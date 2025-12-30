package org.nailyourinterview.lld.snakes_and_ladder.service;

import lombok.Getter;
import org.nailyourinterview.lld.snakes_and_ladder.enums.ObstacleType;
import org.nailyourinterview.lld.snakes_and_ladder.factory.ObstacleFactory;
import org.nailyourinterview.lld.snakes_and_ladder.model.Board;
import org.nailyourinterview.lld.snakes_and_ladder.model.Dice;
import org.nailyourinterview.lld.snakes_and_ladder.model.Obstacle;
import org.nailyourinterview.lld.snakes_and_ladder.model.Player;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

@Getter
public class Game {
    private final int noOfSnakes;
    private final int noOfLadders;
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;

    public Game(int size, int noOfLadders, int noOfSnakes, int noOfDice) {
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;

        board = new Board(size);
        dice = new Dice(noOfDice);
        players = new ArrayDeque<>();

        initBoardObstacles();
    }

    private void initBoardObstacles() {
        generateObstacles(noOfSnakes, ObstacleType.SNAKE);
        generateObstacles(noOfLadders, ObstacleType.LADDER);
    }

    private void generateObstacles(int count, ObstacleType type) {
        Random random = new Random();
        int size = board.getSize();

        while (count > 0) {
            int up = random.nextInt(size - 1) + 2;
            int down = random.nextInt(up - 1) + 1;

            Obstacle obstacle = ObstacleFactory.createObstacle(type, up, down);
            if (board.addObstacle(obstacle)){
                count--;
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        board.printBoard(players);

        while (players.size() > 1) {
            Player currPlayer = players.poll();
            System.out.println("-----------------------------------");

            int diceRoll = dice.roll();
            System.out.println(currPlayer.getName() + " rolled " + diceRoll);

            int newPosition = board.getNewPosition(currPlayer, diceRoll);

            if (newPosition == currPlayer.getPosition()) {
                players.offer(currPlayer);
                continue;
            }

            currPlayer.setPosition(newPosition);

            if (newPosition == board.getSize()) {
                System.out.println(currPlayer.getName() + " has won the game!");
            } else {
                players.offer(currPlayer);
            }

            board.printBoard(players);
        }
    }
}