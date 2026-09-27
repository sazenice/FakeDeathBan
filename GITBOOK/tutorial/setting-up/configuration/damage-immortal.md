---
description: Tutorial 3.2.5/4
icon: sword
---

# Damage immortal

## Variable: `damage-immortal`

Controls whether players can take damage while [immortality mode](../../../additional-functions.md) is active.

Must be a `true` or `false`

* `true` — players **can** be hurt, but a hit that would kill them is cancelled and their health is restored to 1. They also gain regeneration and saturation when they fall low.
* `false` — players are made fully **invulnerable** and cannot be hurt at all

{% hint style="info" %}
This option has no effect while immortality mode is **off**.
{% endhint %}
