---
description: Tutorial 3/4
icon: sliders
---

# Setting up

### Using commands / gui ( simplest )

I recommend configuring the plugin using commands\
[Here](../../commands.md) is a link to the commands, and the [GUI](../../gui.md) covers the graphical equivalent.

Everything below can also be done with commands:

* [Default gamemode](configuration/default-gamemode.md) — `/defaultgamemode`
* [Default spectator](configuration/default-spectator.md) — `/defaultspectate`
* [Death sound](configuration/death-sound.md) — `/setsound death`
* [Revive sound](configuration/revive-sound.md) — `/setsound revive`
* [Immunity](../../permissions.md) — `/setimmunity`

{% hint style="warning" %}
The remaining options have no command and must be edited in `config.yml`.
{% endhint %}

### Using files ( more advanced )

{% hint style="success" %}
Instructions for configuration using files can be found on the subpages
{% endhint %}

When you run the plugin for the first time, a folder named `FakeDeathBan` will appear in the `plugins` folder. It contains:

```
FakeDeathBan/
├── config.yml
├── immunity/
│   ├── deathban.yml
│   ├── freeze.yml
│   ├── immortality.yml
│   ├── joinquit.yml
│   └── move.yml
└── lang/
    ├── cs_cz.yml
    ├── en_us.yml
    └── sk_sk.yml
```

See [Configuration](configuration/README.md) for what each option does, and [Language](language.md) for translating the plugin.
