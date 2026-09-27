---
description: Commands and their descriptions
icon: terminal
---

# Commands

There are a total of **13** commands.

Most commands accept player names as arguments. Wherever a player can be named, the plugin tab-completes online player names for you.

Permissions are listed per command. Every command requires **OP** by default, except `/spectate`, which is available to **everyone**.

{% hint style="info" %}
The full list of permission nodes, including immunity bypass nodes, is on the [Permissions](permissions.md) page.
{% endhint %}

<details>

<summary><strong>Revive</strong></summary>

Removes the deathban from one, some, or all deathbanned players.

Revived players are unfrozen, switched to the [default gamemode](tutorial/setting-up/configuration/default-gamemode.md), teleported to you, and made visible to everyone again. The [revive sound](tutorial/setting-up/configuration/revive-sound.md) is played.

**Permission:** OP (`fakedeathban.revive`)

**Usage:** `/revive [player...]`

**Aliases:**

* res
* rev
* udb
* undeathban

{% hint style="warning" %}
`/udb` and `/undeathban` still work, but they print a notice that the command has been renamed to `/revive`. Prefer `/revive`.
{% endhint %}

Teleporting only happens when a **player** runs the command. From the console, revived players are simply released where they are.

</details>

<details>

<summary><strong>DefaultSpectate</strong></summary>

Sets the player who will be spectated by default when someone dies.

The target must be **online** — the command stores a player name, not a UUID.

**Permission:** OP (`fakedeathban.defaultspectate`)

**Usage:** `/defaultspectate <player>`

**Aliases:**

* defspectate
* defspec
* defs

</details>

<details>

<summary><strong>Spectate</strong></summary>

Changes who you are spectating.

You must be in **spectator mode** to use this, and you can only spectate yourself back out of a deathban by being revived.

**Permission:** Everyone (`fakedeathban.spectate`)

**Usage:** `/spectate <player>`

**Aliases:**

* spec
* s

</details>

<details>

<summary><strong>FreezeBanned</strong></summary>

Freezes deathbanned players so they cannot move or change their spectate target.

**Permission:** OP (`fakedeathban.freezebanned`)

**Usage:** `/freezebanned [player...]`

**Aliases:**

* fb
* freezeb
* fbanned
* fr

**Notes:**

* With **no arguments**, every online deathbanned player who is not already frozen is frozen.
* With **arguments**, each named player must have a deathban. Players with the [freeze immunity](permissions.md) are skipped.

</details>

<details>

<summary><strong>UnfreezeBanned</strong></summary>

Unfreezes frozen players.

**Permission:** OP (`fakedeathban.unfreezebanned`)

**Usage:** `/unfreezebanned [player...]`

**Aliases:**

* unfreezeb
* unfbanned
* ufb
* unfrb

**Notes:**

* With **no arguments**, every online player is unfrozen.
* With **arguments**, only the named players are unfrozen.

</details>

<details>

<summary><strong>DefaultGamemode</strong></summary>

Sets the gamemode players are switched to when revived.

**Permission:** OP (`fakedeathban.defaultgamemode`)

**Usage:** `/defaultgamemode <gamemode>`

**Aliases:**

* defg
* dgamemode
* defaultg
* dg

**Accepted values:** `survival`, `creative`, `spectator`, `adventure` (**lowercase**)

The value is stored uppercase in the config file. An invalid stored value falls back to `ADVENTURE`.

{% hint style="info" %}
To use the vanilla `defaultgamemode`, type `/minecraft:defaultgamemode`
{% endhint %}

</details>

<details>

<summary><strong>SetSound</strong></summary>

Sets or removes the sound played on death and on revival.

**Permission:** OP (`fakedeathban.setsound`)

**Usage:** `/setsound <death|revive> [sound]`

**Sound format:** a valid namespaced sound ID, for example `minecraft:entity.wither.spawn`

{% hint style="success" %}
The sound argument is tab-completed, so you can type `/setsound death minecraft:` and pick from the list.
{% endhint %}

To **remove** a sound, pass the type with no sound:

```
/setsound death
/setsound revive
```

A removed sound leaves no `death-sound` / `revive-sound` key in the config, and the plugin stays silent.

</details>

<details>

<summary><strong>Immortality</strong></summary>

Toggles immortality mode.

**Permission:** OP (`fakedeathban.immortality`)

**Usage:** `/immortality`

**When turned ON:**

* All deathbanned players are revived
* Players gain infinite **saturation** and are made invulnerable
* A **boss bar** appears
* Everyone is notified with a title, an action bar message, and a sound

**When turned OFF:**

* Saturation and regeneration effects are removed
* Invulnerability is lifted
* The boss bar is hidden
* Everyone is notified

{% hint style="warning" %}
Whether players can still take damage while immortal is controlled separately by [`damage-immortal`](tutorial/setting-up/configuration/damage-immortal.md).
{% endhint %}

Players with the [immortality immunity](permissions.md) are never affected.

</details>

<details>

<summary><strong>SetImmunity</strong></summary>

Toggles an immunity for a player.

**Permission:** OP (`fakedeathban.setimmunity`)

**Usage:** `/setimmunity <player> <immunity>`

**Aliases:**

* setimmune
* setim
* simmune
* sim

**Available immunities:**

| Immunity     | Bypasses                                          |
|--------------|---------------------------------------------------|
| `deathban`   | Not being deathbanned at all                     |
| `freeze`     | Being frozen                                     |
| `move`       | The movement / spectate restriction              |
| `joinquit`   | Join and quit messages being muted                |
| `immortality`| Immortality mode effects                          |

**Notes:**

* Running the command a second time with the same immunity **revokes** it.
* Immunities are stored per type in `plugins/FakeDeathBan/immunity/<immunity>.yml` and are reapplied when the player joins.
* The immunity argument is tab-completed.

</details>

<details>

<summary><strong>ToggleFDB</strong></summary>

Turns all of the plugin's event listeners on or off.

**Permission:** OP (`fakedeathban.togglefdb`)

**Usage:** `/togglefdb`

While listeners are off, deaths are not deathbanned, movement is not restricted, and join/quit messages are not muted. The current state is **not** saved — it resets on restart.

</details>

<details>

<summary><strong>FDBui</strong></summary>

Opens the player management GUI.

**Permission:** OP (`fakedeathban.fdbui`)

**Usage:** `/fdbui`

**Aliases:**

* menu
* open

Players only. See the [GUI](gui.md) page for what each button does.

</details>

<details>

<summary><strong>FDBList</strong></summary>

Shows every deathbanned player with their UUID.

**Permission:** OP (`fakedeathban.fdblist`)

**Usage:** `/fdblist`

</details>

<details>

<summary><strong>SimulateBan</strong></summary>

Fakes the visuals of a player being banned, for testing and for scripted moments.

**Permission:** OP (`fakedeathban.simulateban`)

**Usage:** `/simulateban <player> <message>`

**Aliases:**

* simb
* simban
* fakeb
* fakeban

**What it does:**

1. Broadcasts your message to the whole server
2. Broadcasts `<player> left the game`
3. Plays the configured [death sound](tutorial/setting-up/configuration/death-sound.md)

{% hint style="warning" %}
`/simulateban` is purely cosmetic. It does **not** deathban the player, does not freeze them, and does not change any state. Use `/revive` to actually release a deathbanned player.
{% endhint %}

</details>
