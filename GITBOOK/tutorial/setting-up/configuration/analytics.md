---
description: Tutorial 3.2.8/4
icon: chart-pie
---

# Analytics

## Variable: `analytics`

Sends anonymous analytics reports about the server and the plugin to [bStats](https://bstats.org/plugin/32939).

Must be `true` or `false`

Two anonymous charts are reported: the selected `language`, and whether `debug` is enabled. No player data is collected.

{% hint style="info" %}
Turning this off also stops the update notification from being loaded at startup — see [Updates](updates.md).
{% endhint %}
