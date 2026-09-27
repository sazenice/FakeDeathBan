---
description: Tutorial 3.2.10/4
icon: language
---

# Custom language

## Variable: `custom-language`

Controls whether the plugin overwrites your language files on startup.

Must be `true` or `false`

* `false` (default) — the `lang/*.yml` files are re-saved on every startup, synchronising them with the messages the plugin actually uses. You can still edit them, but your changes may be overwritten on the next restart.
* `true` — the plugin leaves your language files alone, so your edits persist. The risk is that the files drift out of sync with the plugin.

{% hint style="warning" %}
Enable at your own risk
{% endhint %}

{% hint style="info" %}
To make custom messages stick reliably, set this to `true` **and** restart the server once. Any message key that the plugin no longer uses can be safely left in the file.
{% endhint %}
