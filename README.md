# BBeeChecker
A simple, lightweight, and configurable plugin that allows you to check how many bees are in a hive. It also adds lore to the hive that says how many bees are inside.

![lore](https://cdn.modrinth.com/data/ixAaxHSq/images/48db5fddae796e6e24d0d6b89b40942c3585380d.png)

## Commands & Permissions
| Command | Permission | Default | Description |
|---|---|---|---|
| `/bb` | `none` | `true` | Display plugin version |
| `/bb reload` | `bbeechecker.reload` | `false` | Reload config |

## Config
| Option | Default | Description |
|---|---|---|
| `ClickOnBlock` | `true` | Report number of bees when a player right clicks on a hive |
| `ClickInAir` | `false` | Report number of bees when a player right clicks with a hive in their hand |
| `MessageLocation` | `chat` | Where you want the number of bees to be shown (`chat` or `hotbar`) |
| `lore` | `true` | Add lore to hives (This will persist after plugin is uninstalled) |
| `Lore.AmountSingle` | `§rThere is §6%number% bee §rinside.` | Lore message for one bee in a hive |
| `Lore.AmountPlural` | `§rThere are §6%number% bees §rinside.` | Lore message for multiple bees in a hive |
| `ChatMessage.AmountSingle` | `§rThere is §6%number% bee §rinside.` | Chat message for one bee in a hive |
| `ChatMessage.AmountPlural` | `§rThere are §6%number% bees §rinside.` | Chat message for multiple bees in a hive |
| `InvalidCommand` | `§cInvalid Command Usage!` | Message for invalid command |
| `ReloadingConfig` | `§6Reloading Config!` | Config reload start message | 
| `ReloadedConfigSuccess` | `§eSuccessfully Reloaded Config!` | Config was successfully reloaded message |
| `ReloadedConfigFail` | `§cFailed to Reload Config!` | Config failed to reload message |
| `disableMetrics` | `false` | Set to true to disable [metrics](https://bstats.org/plugin/bukkit/BBeeChecker/6414)

![bstats](https://bstats.org/signatures/bukkit/BBeeChecker.svg)
