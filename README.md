<div align="center">

# ⚔️ CombatCooldown Plugin

**A powerful combat management system for Lumi servers with anti-combat-log protection and DGuard integration**

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Lumi](https://img.shields.io/badge/Lumi-1.3.0-green.svg)
![Java](https://img.shields.io/badge/Java-21-orange.svg)
![DGuard](https://img.shields.io/badge/DGuard-3.0-supported-brightgreen.svg)

</div>

---

## 📖 Overview

**CombatCooldown** is a comprehensive combat management plugin designed for Minecraft Bedrock Edition servers running on Lumi. The plugin prevents players from leaving the server during active combat, ensuring fair and balanced gameplay. When a player engages in combat, they enter a cooldown period where they cannot safely disconnect or use commands until the timer expires.

### 🎯 What does it do?

- 🛡️ **Anti-Combat-Log Protection** - Players who disconnect during combat will automatically die
- ⏱️ **Visual Timer** - Beautiful ActionBar display showing remaining combat time
- 🚫 **Command Restrictions** - Prevents command usage during active combat mode
- 🗺️ **DGuard Integration** - Respects DGuard region flags for smart zone-based combat control
- ⚙️ **Fully Customizable** - All messages and timers can be configured to your needs

---

## ✨ Features

### 🎮 Core Features

| Feature | Description |
|---------|-------------|
| 🔒 **Combat Lock** | Players cannot leave the server during combat or they will die automatically |
| 📊 **ActionBar Timer** | Visual countdown display showing time remaining in combat mode |
| 💬 **Custom Messages** | Fully customizable messages for all combat events |
| ⚔️ **Command Blocking** | Prevents command usage while in combat mode for fair gameplay |
| 🛡️ **Auto Completion** | Combat automatically ends when a player dies |

### 🔌 DGuard Integration

This plugin features **built-in support** for the DGuard plugin:

- ✅ **Auto-detection** - Automatically detects if DGuard is installed
- ✅ **Smart Region Checking** - Respects DGuard region PvP flags before allowing combat
- ✅ **Soft Dependency** - Works perfectly even without DGuard installed
- ✅ **Protected Zones** - Prevents PvP in regions where it's disabled via DGuard
- ✅ **Damage Blocking** - Cancels damage if PvP is disabled in the region

#### 🔍 How does it work?

The plugin checks the `pvp` flag in DGuard regions for both players (attacker and victim) before allowing entry into combat mode. If PvP is disabled in any of the regions where players are located, combat is blocked and damage is canceled.

```java
// The plugin automatically checks:
// 1. The region where the victim is located
// 2. The region where the attacker is located
// 3. The PvP flag in both regions
// If PvP is disabled → combat is blocked
```

---

## ⚙️ Configuration

The plugin comes with a fully customizable configuration file (`config.yml`). You can adjust combat duration, messages, and all text strings to match your server's theme.

### Configuration Options

```yaml
# CombatCooldown Plugin Settings (Lumi)

# Total time in seconds while the player is considered in combat
total-seconds: 20

# ActionBar text. Use %enemy% and %seconds%
text-bar: "§cCombat with §f%enemy% §7(| §e%seconds% sec.§7)"

# Message when entering combat
text-entered: "§6You entered combat! Do not leave the game."

# Message when combat ends
text-quit: "§aYou left combat."

# Message when trying to use commands during combat
text-on-command: "§cYou cannot use commands during combat!"
```

### 🎨 Message Customization

All messages support Minecraft color codes (`§` or `&`) and placeholders:
- `%enemy%` - Enemy name
- `%seconds%` - Remaining seconds in combat

**Examples:**

```yaml
# Bright and colorful variant
text-bar: "§c⚔ §cCombat §7against §e%enemy% §7| §a⏱ §b%seconds%§7sec"

# Minimalistic variant
text-bar: "§7[§c⚔§7] §f%enemy% §7| §e%seconds%§7s"

# Informative variant
text-bar: "§l§c⚠ COMBAT MODE §r§7| §f%enemy% §7| Remaining: §a%seconds%§7sec"
```

---

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `combatcooldown.bypass` | Allows bypassing plugin restrictions (can leave server during combat) | `false` |

## 📦 Installation

### Quick Setup

1. **Download** the latest version from the [Releases](https://github.com/GamerPlay3975/CombatCooldown-Lumi/releases) section
2. **Place** the `CombatCooldown.jar` file into your server's `plugins` folder
3. **Restart** your server to load the plugin
4. **(Optional)** Install the [DGuard](https://github.com/qPexLegendary/DGuard-Nukkit) plugin for region-based combat control
5. **Configure** the plugin by editing `plugins/CombatCooldown/config.yml`
6. **Reload** the plugin or restart your server

### System Requirements

- **Server**: Lumi 1.3.0+
- **Java**: Java 21+
- **Platform**: Minecraft Bedrock Edition
- **DGuard**: 3.0+ (optional, for region control)

### Dependencies

The plugin uses a soft dependency on DGuard - it works both with and without DGuard installed:
- **With DGuard**: Full integration with region checking
- **Without DGuard**: All features work, but without region checking

---

## 🔧 Usage

### Basic Usage

1. **Entering Combat**: When a player attacks another player, both enter combat mode
2. **Timer**: ActionBar shows countdown time
3. **Protection**: Player cannot leave the server or use commands
4. **Completion**: Combat ends after the timer expires or player dies

### Working with DGuard

1. **Create a region** using DGuard
2. **Set the PvP flag** in the region settings:
   - `true` - PvP allowed
   - `false` - PvP disabled
3. **The plugin automatically** respects these settings

**Example:**
```
Region: "spawn"
PvP Flag: false

→ Combat will be blocked in this region
→ Player will receive message: "§cPvP is disabled in this area"
```
---

## 🤝 Contributing

Contributions are welcome! If you have ideas for improvements or find bugs:

1. **Fork** the repository
2. **Create** a feature branch
3. **Commit** your changes
4. **Push** to the branch
5. **Open** a Pull Request

---

## ⭐ Supporting the Project

If this plugin was helpful, please give it a ⭐ on GitHub!

---

## 📝 License

This project is distributed under the MIT license. See the `LICENSE` file for details.

---

<div align="center">

**Made with ❤️ for the [Lumi](https://discord.gg/HkTnrkUySJ)** community

[⬆ Back to Top](#-combatcooldown-plugin)

</div>

## 📚 Credits

This plugin is based on the following open-source repositories:

- [PVPCooldown](https://github.com/4AK1LLA/PVPCooldown) - Original PvP cooldown plugin
- [DGuard](https://github.com/qPexLegendary/DGuard-Nukkit) - Region protection system
