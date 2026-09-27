---
description: Tutorial 3.2.11/4
icon: bug
---

# Debug

## Variable: `debug`

If debug mode is enabled.

Must be `true` or `false`

When enabled, the plugin logs its startup to the console — registered listeners, registered commands, the boss bar initialisation, and whether the update checker and bStats were loaded or skipped.

{% hint style="warning" %}
Turn this **on** before reporting a bug, and include the console output in your report. Debug mode is also reported to bStats as an anonymous chart.
{% endhint %}
