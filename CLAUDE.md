# Claude Code Configuration & Guidelines

@AGENTS.md

## Claude Code Quick Reference
- **Active Skills:** See [skills/frogo-sdk/SKILL.md](skills/frogo-sdk/SKILL.md) and [.agents/skills/frogo-sdk/SKILL.md](.agents/skills/frogo-sdk/SKILL.md).
- **Core Verification Command:** `./gradlew testDebugUnitTest`
- **Deprecation Policy:** Never use `@Suppress("DEPRECATION")`. Always migrate to modern Android/Compose/Ads APIs as documented in `AGENTS.md`.
