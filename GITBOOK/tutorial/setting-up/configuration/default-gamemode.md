---
description: Tutorial 3.2.1/4
icon: joystick
---

# Default gamemode

## Variable: `default-gamemode`

The gamemode players are switched to when they are [revived](../../../commands.md).

Values it can be set to and their ratings **(CASE SENSITIVE)**:

1. SPECTATOR (⭐)
2. SURVIVAL (⭐⭐⭐)
3. CREATIVE (⭐)
4. **ADVENTURE (⭐⭐⭐⭐⭐)**

**Notes:**

* When set with [`/defaultgamemode`](../../../commands.md), the value must be **lowercase** — `survival`, `creative`, `spectator`, or `adventure`. The command stores it uppercase for you.
* An invalid stored value falls back to `ADVENTURE` instead of erroring.
* This only affects the gamemode on revive. Dying still switches the player to spectator mode.
