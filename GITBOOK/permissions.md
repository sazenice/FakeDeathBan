---
description: Permission nodes and immunities
icon: lock
---

# Permissions

FakeDeathBan uses two groups of permission nodes: **command** nodes, and **immunity** nodes.

## Command permissions

Every command node defaults to **OP**, with one exception.

| Node                             | Default   | Command                             |
|----------------------------------|-----------|-------------------------------------|
| `fakedeathban.revive`            | OP        | `/revive`                           |
| `fakedeathban.defaultspectate`   | OP        | `/defaultspectate`                  |
| `fakedeathban.spectate`          | Everyone  | `/spectate`                         |
| `fakedeathban.freezebanned`      | OP        | `/freezebanned`                     |
| `fakedeathban.unfreezebanned`    | OP        | `/unfreezebanned`                   |
| `fakedeathban.defaultgamemode`   | OP        | `/defaultgamemode`                  |
| `fakedeathban.setsound`          | OP        | `/setsound`                         |
| `fakedeathban.immortality`       | OP        | `/immortality`                      |
| `fakedeathban.setimmunity`       | OP        | `/setimmunity`                      |
| `fakedeathban.togglefdb`         | OP        | `/togglefdb`                        |
| `fakedeathban.fdbui`             | OP        | `/fdbui`                            |
| `fakedeathban.fdblist`           | OP        | `/fdblist`                          |
| `fakedeathban.simulateban`       | OP        | `/simulateban`                      |

{% hint style="info" %}
`/spectate` is the only command open to everyone by default, so that dead players can choose who to watch. Reviving a player always requires OP.
{% endhint %}

{% hint style="warning" %}
Nodes from older versions (`fakedeathban.check`, `fakedeathban.version`, `fakedeathban.undeathban`, `fakedeathban.freeze`) no longer exist and can be removed from your permission plugin.
{% endhint %}

## Immunity permissions

Immunity nodes are the opposite of command nodes: they default to **false**, so nobody has them unless you grant them.

| Node                                  | Grants immunity against        |
|---------------------------------------|--------------------------------|
| `fakedeathban.bypass.deathban`         | Being deathbanned             |
| `fakedeathban.bypass.freeze`           | Being frozen                  |
| `fakedeathban.bypass.move`             | Movement / spectate restriction|
| `fakedeathban.bypass.joinquit`         | Join and quit message muting  |
| `fakedeathban.bypass.immortality`      | Immortality mode              |

These are usually given with [`/setimmunity`](commands.md), which toggles the node for a single player and stores the result in `plugins/FakeDeathBan/immunity/`.

They can also be granted permanently through a permission plugin — a global grant applies to everyone, while a per-player grant behaves the same as `/setimmunity`.

{% hint style="warning" %}
`fakedeathban.bypass.deathban` is a full bypass. A player with it is never added to the deathban list in the first place, so they will not be revived, spectated, or frozen by this plugin.
{% endhint %}

## Notes

* Permissions are checked live, so granting or revoking one takes effect without a restart.
* Immunity attachments granted by `/setimmunity` are removed again when the player quits or the plugin is disabled, then re-applied from the `immunity/*.yml` files on their next join.
* The immunity files store UUIDs, so immunities survive a player rename.
