package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;

public class RandomBot extends Bot {
    protected RandomBot(Field field) {
        super(field);
    }
    @Override
    protected Move getAction() {
        return new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY);
    }

    public static class Factory extends Bot.BotFactory {
        @Override
        public RandomBot createBot(final Field field) {
            return new RandomBot(field);
        }
    }
}
