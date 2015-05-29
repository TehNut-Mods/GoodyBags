#GoodyBags
Allows you to create custom loot bags (similar to the Thaumcraft loot bags) through JSON files. You can find information about that [here](https://github.com/TehNut/GoodyBags/wiki). 

##Bag Types
There are currently 3 types of bags you can create:
 
- **Spawn-** Spawns in a player's inventory when they first join the world. Meant to replace the inventory spam that has become current modded Minecraft. I guess you can use it for other things, too.
- **Loot-** Generates in dungeon chests with a configurable weight.
- **Prize-** Meant to be given to players as a reward for completing something. Similar to the HQM bags.

Currently there is no support for NBT. I will look into that at a later date.

##Examples

###Spawn Bag
    {
        "bagType": "spawn",
        "name": "Spawn Bag 1",
        "rarityType": "uncommon",
        "stackList": [
            {
                "name": "minecraft:chainmail_helmet",
                "metadata": 0,
                "amount": 1
            },
            {
                "name": "minecraft:diamond_block",
                "metadata": 0,
                "amount": 8
            }
        ]
    }

###Loot Bag
    {
        "bagType": "LOOT",
        "name": "Loot Bag 1",
        "rarityType": "rare",
        "lootChance": 5,
        "stackList": [
            {
                "name": "minecraft:chainmail_helmet",
                "metadata": 0,
                "amount": 1
            },
            {
                "name": "minecraft:diamond_block",
                "metadata": 0,
                "amount": 8
            }
        ]
    }

###Prize Bag
    {
        "bagType": "prize",
        "name": "Loot Bag 1",
        "rarityType": "epic",
        "lootChance": 5,
        "stackList": [
            {
                "name": "minecraft:chainmail_helmet",
                "metadata": 0,
                "amount": 1
            },
            {
                "name": "minecraft:diamond_block",
                "metadata": 0,
                "amount": 8
            }
        ]
    }