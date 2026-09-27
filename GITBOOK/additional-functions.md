---
description: Useful, but not needed
icon: function
---

# Additional functions

## Management GUI

A graphical player manager for revives, freezes, kills, and immunities.

**Command**: `/fdbui`

See the [GUI](gui.md) page for the full button list.

## Immortality Mode

\
In Immortality Mode, players[^1] will be **invulnerable** and will have the **saturation** **effect**.

{% hint style="info" %}
Depends on the [configuration](tutorial/setting-up/configuration/damage-immortal.md) of the server
{% endhint %}

All players will be notified of this change.

At the same time, all players will be unfrozen and revived.

A boss bar will appear

**Command**: `/immortality`

**Usage**: When an event is being prepared

## Immunity

Immunity can be used against:

* **Deathban**
* **Freeze**
* **Muting join and quit messages**
* **Movement restriction with deathban (move)**
* **Immortality**

\
**Command**: `/setimmunity <player> <immunity>`

Running the command again with the same immunity revokes it.

**Usage**: For players with special permissions

The same nodes can also be granted permanently through a permission plugin — see [Permissions](permissions.md).

## Banlist

See every deathbanned player together with their UUID.

**Command**: `/fdblist`

**Usage**: When you need to check who is currently deathbanned

## Simulated ban

Broadcast a fake ban message, a "left the game" line, and the death sound — without actually deathbanning anyone.

**Command**: `/simulateban <player> <message>`

**Usage**: For scripted moments and for testing your death message formatting

## Autocomplete

Autocomplete can be used for some commands so you don't have to type the entire command yourself

| Command                                | Completes                                   |
|----------------------------------------|---------------------------------------------|
| `/setsound`                            | `death` / `revive`, then sound IDs           |
| `/revive`                              | Online player names                          |
| `/defaultspectate`                     | Online player names                          |
| `/spectate`                            | Online player names                          |
| `/defaultgamemode`                     | `survival` / `creative` / `spectator` / `adventure` |
| `/setimmunity`                         | Online player names, then immunity types     |

**Usage**: When you don't know all the commands and their arguments

## Toggle listeners

Turn every one of the plugin's event listeners on or off in one go. Useful when you want to run something else on your server without unloading the plugin.

**Command**: `/togglefdb`

**Usage**: Temporarily disabling FakeDeathBan without a restart

The state is not saved and resets to **on** after a restart.

## Debug mode

If you want to report issues, you need to have this option ON

**Enable**: In [config.yml](tutorial/setting-up/configuration/debug.md), `debug` boolean

**Usage**: For reporting issues

[^1]: Players without the `fakedeathban.bypass.immortality` permission
