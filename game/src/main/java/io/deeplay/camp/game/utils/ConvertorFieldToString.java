package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;

public class ConvertorFieldToString {

    public static String convertFieldToString(Field field) {
        StringBuilder result = new StringBuilder();
        Cell[][] board = field.getBoard();

        result.append(' ');
        result.append(' ');
        for (int j = 0; j < field.getSize(); j++) {
            result.append("  ");
            result.append(j);
            result.append("     ");
        }
        result.append('\n');

        //верхняя граница поля
        result.append(' ');
        result.append('┌');
        for (int j = 0; j < field.getSize(); j++) {
            result.append("─".repeat(7));
            result.append(j == field.getSize() - 1 ? '┐' : '┬');
        }
        result.append('\n');


        for (int i = 0; i < field.getSize(); i++) {
            result.append((char) ('A' + i));
            //информация о планетах
            result.append('│');
            for (int j = 0; j < field.getSize(); j++) {
                result.append(' ');
                if (board[i][j].planet != null) {
                    if (board[i][j].planet.isCaptured()) {
                        result.append('●');
                    } else {
                        result.append('◯');
                    }
                    result.append(' ');
                    result.append((char) (board[i][j].planet.points + '0'));
                    result.append(" ".repeat(3));
                } else {
                    result.append(" ".repeat(6));
                }
                result.append('│');
            }
            result.append('\n');

            result.append(' ');
            //информация о кораблях
            result.append('│');
            for (int j = 0; j < field.getSize(); j++) {
                result.append(' ');
                if (board[i][j].getFleet() != null) {
                    result.append('ᴥ');
                    result.append(' ');
                    result.append(board[i][j].getFleet().getFleetPower());
                    if (board[i][j].getFleet().getFleetPower() < 999) {
                        result.append(' ');
                    }
                } else {
                    result.append(" ".repeat(6));
                }
                result.append('│');
            }
            result.append('\n');
            result.append(' ');

            // нижняя граница строки
            if (i != field.getSize() - 1) {
                result.append('├');
                for (int j = 0; j < field.getSize(); j++) {
                    result.append("─".repeat(7));
                    result.append(j == field.getSize() - 1 ? '┤' : '┼');
                }
                result.append('\n');

            }
        }

        // нижняя граница последней строки
        result.append('└');
        for (int j = 0; j < field.getSize(); j++) {
            result.append("─".repeat(7));
            result.append(j == field.getSize() - 1 ? '┘' : '┴');
        }
        result.append('\n');

        return result.toString();
    }
}
