#!/usr/bin/env bash
# Installs and syncs the Frogo SDK skill for AI coding agents across projects.
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
SOURCE_SKILL_DIR="$REPO_ROOT/skills/frogo-sdk"

if [ ! -d "$SOURCE_SKILL_DIR" ]; then
    echo "Source skill directory not found: $SOURCE_SKILL_DIR"
    exit 1
fi

echo "📦 Frogo SDK Skill Installer"
echo "Source: $SOURCE_SKILL_DIR"

USER_HOME="$HOME"

# Global paths
GLOBAL_DESTS=(
    "$USER_HOME/.gemini/config/skills/frogo-sdk"
    "$USER_HOME/.claude/skills/frogo-sdk"
    "$USER_HOME/.agents/skills/frogo-sdk"
)

for dest in "${GLOBAL_DESTS[@]}"; do
    mkdir -p "$dest"
    # Remove obsolete files
    rm -f "$dest/references/recyclerview-reference.md" 2>/dev/null || true
    cp -R "$SOURCE_SKILL_DIR"/* "$dest"/
    echo "  ✅ Installed to global: $dest"
done

# Check optional target project arg (-t /path/to/project)
TARGET_PROJECT=""
while [[ $# -gt 0 ]]; do
    case $1 in
        -t|--target)
            TARGET_PROJECT="$2"
            shift 2
            ;;
        *)
            shift
            ;;
    esac
done

if [ -n "$TARGET_PROJECT" ]; then
    if [ ! -d "$TARGET_PROJECT" ]; then
        echo "Target project directory does not exist: $TARGET_PROJECT"
        exit 1
    fi
    TARGET_SKILL_DEST="$TARGET_PROJECT/.agents/skills/frogo-sdk"
    mkdir -p "$TARGET_SKILL_DEST"
    rm -f "$TARGET_SKILL_DEST/references/recyclerview-reference.md" 2>/dev/null || true
    cp -R "$SOURCE_SKILL_DIR"/* "$TARGET_SKILL_DEST"/
    echo "  ✅ Copied skill to project: $TARGET_SKILL_DEST"

    cat <<EOF > "$TARGET_PROJECT/.agents/skills.json"
{
  "entries": [
    {
      "path": "skills"
    }
  ]
}
EOF
    echo "  ✅ Configured: $TARGET_PROJECT/.agents/skills.json"
fi

echo ""
echo "🎉 Frogo SDK skill successfully installed and ready for all AI agents!"
