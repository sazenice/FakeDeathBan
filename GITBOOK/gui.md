---
description: The player management GUI
icon: table-cells
---

# GUI

FakeDeathBan ships with a graphical player manager, opened with [`/fdbui`](commands.md).

{% hint style="info" %}
The GUI is **player only** — it cannot be opened from the console — and requires the `fakedeathban.fdbui` permission (OP by default).
{% endhint %}

## Main menu

The first menu is titled **FDB** and lists the head of every player currently online. Click a head to manage that player.

## Player menu

The second menu is titled **FDB - \<player\>** and holds one button per action. Clicking a button runs the equivalent command for you and plays a level-up sound.

| Item              | Action                                                     |
|-------------------|------------------------------------------------------------|
| Beacon            | `/revive <player>` — only works if the target is deathbanned |
| Diamond sword     | Kill the player, triggering a normal death                 |
| Ender eye         | `/defaultspectate <player>`                                |
| Snowball          | Freeze the player                                           |
| Totem of undying  | Toggle `deathban` immunity                                 |
| Hay block         | Toggle `immortality` immunity                              |
| Piston            | Toggle `move` immunity                                     |
| Snow block        | Toggle `freeze` immunity                                   |
| Oak door          | Toggle `joinquit` immunity                                 |

{% hint style="warning" %}
The GUI's freeze button is currently broken — it dispatches a `/freeze` command that no longer exists after the rename to `/freezebanned`. Use the command until this is fixed. Every other button works.
{% endhint %}

{% hint style="warning" %}
The GUI also requires the `fakedeathban.gui` permission for its clicks to register. That node is not declared in `plugin.yml` yet, so on most setups the menus open but the buttons do nothing. Grant it explicitly, or use the commands on the [Commands](commands.md) page instead.
{% endhint %}
