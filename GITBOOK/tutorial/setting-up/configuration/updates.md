---
description: Tutorial 3.2.9/4
icon: square-up
---

# Updates

## Variable: `updates`

If the plugin should check for new versions and notify you.

Must be `true` or `false`

When enabled, the plugin queries the Modrinth API on startup. If a newer version exists, the console is notified and every **OP who joins** is told in chat.

{% hint style="info" %}
The check is fully anonymous and only reads the public version list. It is skipped entirely when set to `false`.
{% endhint %}
