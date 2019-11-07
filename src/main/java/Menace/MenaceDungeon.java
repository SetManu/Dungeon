package Menace;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;

import java.util.Random;

public class MenaceDungeon extends Dungeon{

	private Random rand = new Random();

	private String[] potionNames = {"Water", "Small Potion", "Potion", "Big Potion",};
	private String[] shieldsNames = {"Broken Shield", "Shield", "Iron Shield",};
	private String[] weaponName = { "Stick", "Golden Sword", "Katana", "Broken Sword", "Sword", "Iron Sword",};
	private String[] enemyNames = {"Skeleton", "Ork", "Dragon", "Ghoul", "Zombie", "Dark Elb", "Goblin", "Slime",
			"Mummy", "You Mom", "Your non existing Girlfriend", "Dark Magician"
	};

	public MenaceDungeon(RoomList rooms) {
		super(rooms);
	}

	/**
	 * This Method will bring you to the next room. Its sets the currentRoom: FourDoorRoom.
	 *
	 * @param nextRoom An index that will set the current room out of the rooms list.
	 */
	@Override
	public void gotoNextRoom(int nextRoom) {
		if (nextRoom == 0) reSpawnEnemies();
		currentRoom = rooms.get(nextRoom);
	}

	/**
	 * Spawn new enemies in the rooms List. Call this Method after you have
	 * created a dungeon to Spawn Enemies in the dungeon.
	 */
	@Override
	public void spawnEnemies() {
		for (int i = 0; i < rooms.size(); i++) {
			FourDoorRoom room = rooms.get(i);
			room.setItems(new ItemList());
			if (i == 4 || i == 9) {
				room.getItems().add(createPotion(room.getCurrentRoomNumber()));
			}
			room.setEnemy(createEnemy(room.getCurrentRoomNumber()));
		}
	}

	/**
	 * ReSpawn dead enemies. Call this to re spawn enemies in a dungeon.
	 */
	@Override
	protected void reSpawnEnemies() {
		for (int i = 0; i < rooms.size(); i++) {
			FourDoorRoom room = rooms.get(i);
			room.setItems(new ItemList());
			room.setCurrentRoomNumber(room.getCurrentRoomNumber() + 10);
			room.setRoomName("Room " + room.getCurrentRoomNumber());
			if (i == 4 || i == 9) {
				room.getItems().add(createPotion(room.getCurrentRoomNumber()));
			}
			resetEnemy(room.getEnemy(), room.getCurrentRoomNumber());
		}
	}

	@Override
	public void dropEnemyItems() {
		// TODO: NEW DROP SYSTEM
		if (currentRoom.getCurrentRoomNumber() % 5 == 0) {
			currentRoom.getItems().add(currentRoom.getItems().size(), currentRoom.getEnemy().getRightHandWeapon());
		}
		else if (currentRoom.getCurrentRoomNumber() % 10 == 0) {
			currentRoom.getItems().add(currentRoom.getItems().size(), currentRoom.getEnemy().getRightHandWeapon());
			currentRoom.getItems().add(currentRoom.getItems().size(), currentRoom.getEnemy().getLeftHandShield());
		}
	}

	private void resetEnemy(Enemy enemy, int roomNumber) {
		enemy.setALife(true);
		enemy.setName(enemyNames[rand.nextInt(enemyNames.length)]);
		enemy.setRightHandWeapon(createWeapon(roomNumber));
		enemy.setLeftHandShield(createShield(roomNumber));
	}

	private Potion createPotion(int roomNumber) {
		return new Potion(potionNames[rand.nextInt(potionNames.length)],
				(roomNumber + 1) * 3 / 2);
	}

	private Weapon createWeapon(int roomNumber) {
		return new Weapon(weaponName[rand.nextInt(weaponName.length)],
				(roomNumber + 3) * 2 - 2);
	}

	private Shield createShield(int roomNumber) {
		return new Shield(shieldsNames[rand.nextInt(shieldsNames.length)],
				(roomNumber + 3) * 2 - 3);
	}

	private Enemy createEnemy(int roomNumber) {
		int i = rand.nextInt(roomNumber + 1);
		return new Enemy(enemyNames[rand.nextInt(enemyNames.length)], true,
				(roomNumber + 10 + i) / 2,
				createWeapon(roomNumber), createShield(roomNumber));
	}
}