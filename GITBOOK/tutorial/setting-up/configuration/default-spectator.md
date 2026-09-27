---
description: Tutorial 3.2.2/4
icon: camera
---

# Default spectator

## Variable: `default-spectator`

The player who dead players are forced to spectate.

The value is a player **name**, and that name must be supported by Minecraft.

**Notes:**

* The player should be **online**. If the configured player is offline — or if the dead player *is* the default spectator — the dead player cannot move and is told to spectate someone else.
* You can only set this to a player who is currently online, using [`/defaultspectate`](../../../commands.md).
* Dead players can switch to a different target themselves with [`/spectate`](../../../commands.md), which is available to everyone.
