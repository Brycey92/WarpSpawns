# WarpSpawns
This plugin uses Nucleus warps to enable multiple spawn points for users. For example, one could have separate groups in LuckPerms, where each has their own spawn point. This can be used for factions, rank-specific perks, giving each player their own spawn, etc.

### Usage
WarpSpawns requires Nucleus version 1.13.0-SNAPSHOT-S7.1 or later.

Warps used with this plugin must be in a warp category named `spawns` (case-insensitive) in Nucleus.

### Permissions
| Permission                          | Description                                                                                                                                                                   |
|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `warpspawns.<warpname>.command`     | Spawns the user to the given Nucleus warp on `/spawn`, where `<warpname>` is the name of the warp.                                                                            |
| `warpspawns.<warpname>.death`       | Spawns the user to the given Nucleus warp on death, where `<warpname>` is the name of the warp.                                                                               |
| `warpspawns.<warpname>.homeondeath` | Spawns the user to the given Nucleus warp on death when Nucleus would have sent them to their default home instead, where `<warpname>` is the name of the warp.               |
| `warpspawns.<warpname>.other`       | Spawns the user to the given Nucleus warp on spawns that are neither from command nor death, or if Nucleus doesn't give a reason, where `<warpname>` is the name of the warp. |

### Credits
Thanks to Snowie (D4rk), Blake Anderson, and a huge thanks to doot for helping me create this plugin and modify Nucleus to support it!
All credit for the icon goes to doot.