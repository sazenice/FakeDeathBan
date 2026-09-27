---
description: Tutorial 3.1/4
icon: flag
---

# Language

### Can be found in `FakeDeathBan/lang/`<kbd>`language`</kbd>`.yml` (for example en\_us.yml)

In this file, you can edit or modify messages from the plugin.

### Available languages

| Value    | Language         |
|----------|------------------|
| `en_us`  | English (US)     |
| `cs_cz`  | Czech            |
| `sk_sk`  | Slovak           |

Select one with the `language` variable in [config.yml](configuration/README.md):

```yaml
language: "en_us"
```

### Making your edits stick

By default the plugin re-saves all language files on every startup, so your changes are overwritten.

To keep your edits, set [`custom-language`](configuration/custom-language.md) to `true` and restart the server once.

{% hint style="info" %}
Messages are plain text with `%s` placeholders. Replace a placeholder with your own text, or leave it to insert a player name, a sound, or a gamemode.
{% endhint %}

{% hint style="warning" %}
Adding or removing message keys can desynchronise your file from the plugin. Only change the **text**, not the keys.
{% endhint %}
