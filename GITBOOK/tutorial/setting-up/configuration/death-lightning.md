---
description: Tutorial 3.2.7/4
icon: bolt-lightning
---

# Death lightning

## Variable: `deathlightning`

If a lightning strike should be summoned where a player died.

Must be `true` or `false`

The strike is a **visual effect only** — it deals no damage and does not set anything on fire.

{% hint style="warning" %}
Lightning is currently only triggered when a [`death-sound`](death-sound.md) is configured. If you remove the death sound, no lightning will be summoned even with this set to `true`.
{% endhint %}
