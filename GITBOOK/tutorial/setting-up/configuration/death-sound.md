---
description: Tutorial 3.2.3/4
icon: skull
---

# Death sound

## Variable: `death-sound`

### A sound which will be played after a death

Must be a valid minecraft sound (`type.object.sound`)

Easiest way to set it: [`/setsound death <sound>`](../../../commands.md), which tab-completes sound IDs for you.

To **remove** the sound and make deaths silent, run `/setsound death` with no sound. The key is then deleted from the config.

{% hint style="warning" %}
Death [lightning](death-lightning.md) is currently tied to this option — if the death sound is removed, no lightning is summoned either.
{% endhint %}
