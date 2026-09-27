---
description: Tutorial 3.2/4
icon: gears
---

# Configuration

### Can be found in `FakeDeathBan/config.yml`

Contains all plugin information that is retained after a server restart (non-cached)

Most of these can also be changed at runtime with [commands](../../../commands.md), which is usually easier.

## Options

| Variable           | Default                                  | What it does                                        |
|--------------------|------------------------------------------|-----------------------------------------------------|
| `default-spectator`| `sazenice`                               | Who dead players spectate                           |
| `default-gamemode` | `ADVENTURE`                              | Gamemode set on revive                              |
| `death-sound`      | `minecraft:entity.wither.spawn`          | Sound played on death                               |
| `revive-sound`     | `minecraft:block.beacon.activate`        | Sound played on revive                              |
| `language`         | `en_us`                                  | Active language file                                |
| `damage-immortal`  | `true`                                   | Whether immortal players can take damage            |
| `hide-invis`       | `true`                                   | Hide invisible killers in death messages            |
| `deathlightning`   | `true`                                   | Summon lightning where a player died                |
| `analytics`        | `true`                                   | Send anonymous bStats analytics                     |
| `updates`          | `true`                                   | Check Modrinth for new versions                     |
| `custom-language`  | `false`                                  | Stop the plugin overwriting `lang/*.yml`            |
| `debug`            | `false`                                  | Verbose startup logging                             |

## Managed for you

These two lists are written by the plugin. **Do not modify them by hand** — use [commands](../../../commands.md) or the [GUI](../../../gui.md) instead.

| List         | Contents                                        |
|--------------|-------------------------------------------------|
| `deathbanned`| UUIDs of every deathbanned player              |
| `frozen`     | UUIDs of every frozen player                    |

Because they store UUIDs, deathbans and freezes survive a server restart and a player rename.

{% hint style="warning" %}
Editing these by hand risks corrupting the plugin's state. If you need to clear them, use `/revive` and `/unfreezebanned`, or delete the whole plugin folder — see [Resetting](../../resetting.md).
{% endhint %}

## Immunity files

A separate `immunity/` folder sits next to `config.yml`, with one file per immunity type:

```
immunity/deathban.yml
immunity/freeze.yml
immunity/immortality.yml
immunity/joinquit.yml
immunity/move.yml
```

Each holds an `immune` list of UUIDs, managed by [`/setimmunity`](../../../commands.md). See [Permissions](../../../permissions.md).
