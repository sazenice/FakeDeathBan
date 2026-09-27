---
description: Tutorial 3.2.4/4
icon: heart
---

# Revive sound

## Variable: `revive-sound`

### A sound which will be played after a revival

Must be a valid minecraft sound (`type.object.sound`)

The sound is played at the location of whoever ran [`/revive`](../../../commands.md), so all revived players hear it.

Easiest way to set it: [`/setsound revive <sound>`](../../../commands.md), which tab-completes sound IDs for you.

To **remove** the sound, run `/setsound revive` with no sound. The key is then deleted from the config.
