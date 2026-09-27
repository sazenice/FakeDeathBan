---
description: Tutorial 3.2.6/4
icon: user-dashed
---

# Hide invisible

## Variable: `hide-invis`

When the killer has the **invisibility** effect, this controls whether their name is revealed in the death message.

* `true` — the killer's name is obfuscated in the death message, so the kill stays anonymous
* `false` — the killer's name is shown normally, even if they are invisible

Must be a `true` or `false`

{% hint style="info" %}
This is what keeps invisible players from being identified by their kill messages on scripted servers.
{% endhint %}
