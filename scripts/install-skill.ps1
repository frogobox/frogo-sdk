<#
.SYNOPSIS
    Installs and syncs the Frogo SDK skill for AI coding agents across projects.
.DESCRIPTION
    Installs the frogo-sdk skill into global AI agent directories (Antigravity/Gemini, Claude Code,
    Universal .agents) or into a target Android project.
.PARAMETER TargetProject
    Path to an Android project root directory to install the skill locally.
.PARAMETER Global
    Switch to install/sync to global AI agent configuration directories.
.EXAMPLE
    .\scripts\install-skill.ps1 -Global
.EXAMPLE
    .\scripts\install-skill.ps1 -TargetProject "D:\AndroidProject\MyAwesomeApp"
#>

[CmdletBinding()]
param (
    [string]$TargetProject = "",
    [switch]$Global = $true
)

$ErrorActionPreference = "Stop"
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$RepoRoot = Split-Path -Parent $ScriptDir
$SourceSkillDir = Join-Path $RepoRoot "skills\frogo-sdk"

if (-not (Test-Path $SourceSkillDir)) {
    throw "Source skill directory not found: $SourceSkillDir"
}

Write-Host "[*] Frogo SDK Skill Installer" -ForegroundColor Cyan
Write-Host "Source: $SourceSkillDir" -ForegroundColor DarkGray

# 1. Global installation
if ($Global) {
    $UserProfile = [System.Environment]::GetFolderPath([System.Environment+SpecialFolder]::UserProfile)

    $GlobalDestinations = @(
        # Antigravity / Gemini CLI
        (Join-Path $UserProfile ".gemini\config\skills\frogo-sdk"),
        # Claude Code
        (Join-Path $UserProfile ".claude\skills\frogo-sdk"),
        # Universal Agents (Codex, Cursor, Copilot)
        (Join-Path $UserProfile ".agents\skills\frogo-sdk")
    )

    foreach ($dest in $GlobalDestinations) {
        $parentDir = Split-Path -Parent $dest
        if (-not (Test-Path $parentDir)) {
            New-Item -ItemType Directory -Path $parentDir -Force | Out-Null
        }
        if (-not (Test-Path $dest)) {
            New-Item -ItemType Directory -Path $dest -Force | Out-Null
        }

        # Clean legacy references if present in destination
        $legacyRecycler = Join-Path $dest "references\recyclerview-reference.md"
        if (Test-Path $legacyRecycler) {
            Remove-Item -Path $legacyRecycler -Force -ErrorAction SilentlyContinue
        }

        Copy-Item -Path "$SourceSkillDir\*" -Destination $dest -Recurse -Force
        Write-Host "  [OK] Installed to global: $dest" -ForegroundColor Green
    }
}

# 2. Target Project Installation
if (![string]::IsNullOrWhiteSpace($TargetProject)) {
    if (-not (Test-Path $TargetProject)) {
        throw "Target project directory does not exist: $TargetProject"
    }

    $ProjectAgentsDir = Join-Path $TargetProject ".agents"
    $ProjectSkillsDir = Join-Path $ProjectAgentsDir "skills"
    $ProjectSkillDest = Join-Path $ProjectSkillsDir "frogo-sdk"

    if (-not (Test-Path $ProjectSkillDest)) {
        New-Item -ItemType Directory -Path $ProjectSkillDest -Force | Out-Null
    }

    Copy-Item -Path "$SourceSkillDir\*" -Destination $ProjectSkillDest -Recurse -Force
    Write-Host "  [OK] Copied skill to project: $ProjectSkillDest" -ForegroundColor Green

    # Ensure skills.json exists and registers 'skills'
    $SkillsJsonPath = Join-Path $ProjectAgentsDir "skills.json"
    $skillsConfig = @{
        entries = @(
            @{ path = "skills" }
        )
    } | ConvertTo-Json -Depth 4
    Set-Content -Path $SkillsJsonPath -Value $skillsConfig -Encoding UTF8
    Write-Host "  [OK] Configured: $SkillsJsonPath" -ForegroundColor Green
}

Write-Host ""
Write-Host "[SUCCESS] Frogo SDK skill successfully installed and ready for all AI agents!" -ForegroundColor Yellow
