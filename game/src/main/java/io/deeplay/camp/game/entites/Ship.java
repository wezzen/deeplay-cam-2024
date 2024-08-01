package io.deeplay.camp.game.entites;

/**
 * Класс – представление для Корабля
 */
public class Ship extends GalaxyEntity {
    /**
     * Корабль имеет:
     * 1) Тип
     * 2) флот, для контроля принадлежности к нему
     */
    final ShipType shipType;
    private Fleet fleetAffiliation;

    public void setFleetAffiliation(final Fleet fleetAffiliation) {
        this.fleetAffiliation = fleetAffiliation;
        this.fleetAffiliation.addShipIntoFleet(this);
    }

    /**
     * Конструктор для корабля
     *
     * @param shipType         тип инициализируемого корабля
     * @param fleetAffiliation флот, к которому относится корабль
     */
    public Ship(final ShipType shipType, final Fleet fleetAffiliation) {
        super();
        this.shipType = shipType;
        setFleetAffiliation(fleetAffiliation);
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Fleet fleetAffiliation() {
        return this.fleetAffiliation;
    }

    /**
     * Набор всевозможных кораблей в игре
     * <p>
     * Все корабли имеют:
     * 1) Очки атаки
     * 2) Имя корабля
     */
    public enum ShipType {
        BASIC(100, "Basic"),
        MEDIUM(150, "Medium"),
        POWERFUL(200, "Powerful");

        private final int shipPower;
        private final String shipName;

        ShipType(int shipPower, String shipName) {
            this.shipPower = shipPower;
            this.shipName = shipName;
        }

        public int getShipPower() {
            return this.shipPower;
        }

        @Override
        public String toString() {
            return this.shipName;
        }

    }

    /**
     * Переопределяет метод {@code equals} для проверки равенства двух объектов класса {@code Ship}.
     * <p>
     * Этот метод проверяет, являются ли два объекта идентичными по следующим критериям:
     * <ul>
     *     <li>Они ссылаются на один и тот же экземпляр.</li>
     *     <li>Они принадлежат к одному и тому же классу.</li>
     *     <li>Их поля {@code shipType} и {@code fleetAffiliation} равны.</li>
     * </ul>
     * <p>
     * Метод сначала проверяет, совпадают ли ссылки на объекты. Если это так, они равны.
     * Затем проверяется, не является ли переданный объект {@code null} и принадлежит ли он к тому же классу.
     * После этого происходит приведение объекта к типу {@code Ship} и сравнение значений полей:
     * <ul>
     *     <li>{@code shipType} сравнивается с помощью {@code ==}, так как это перечисление (enum).</li>
     *     <li>{@code fleetAffiliation} проверяется на {@code null}. Если оно не равно {@code null},
     *     то используется метод {@code equals} для сравнения значений поля. Если оно равно {@code null},
     *     то проверяется, что у другого объекта это поле также равно {@code null}.</li>
     * </ul>
     * <p>
     * Решение проблемы {@code StackOverflowError}:
     * Проблема возникала из-за рекурсивного вызова метода {@code equals} внутри метода {@code equals}
     * из-за некорректного сравнения поля {@code fleetAffiliation}. Теперь сравнение {@code fleetAffiliation}
     * выполняется с учетом возможности {@code null}, что предотвращает бесконечные рекурсии.
     *
     * @param o Объект, с которым сравнивается текущий объект {@code Ship}.
     * @return {@code true}, если объект {@code o} равен текущему объекту {@code Ship}, иначе {@code false}.
     */
    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            System.out.println("Comparing same object reference");
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            System.out.println("Comparing with null or different class");
            return false;
        }
        Ship ship = (Ship) o;
        System.out.println("Comparing ship types: " + shipType + " with " + ship.shipType);
        System.out.println("Comparing fleet affiliations: " + fleetAffiliation + " with " + ship.fleetAffiliation);
        return shipType == ship.shipType &&
                (fleetAffiliation != null ? fleetAffiliation.equals(ship.fleetAffiliation) : ship.fleetAffiliation == null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipType, fleetAffiliation);
    }*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return shipType == ship.shipType &&
                // Сравнение по идентификатору флота для избежания бесконечной рекурсии
                Objects.equals(fleetAffiliation != null ? fleetAffiliation.getId() : null,
                        ship.fleetAffiliation != null ? ship.fleetAffiliation.getId() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipType, fleetAffiliation != null ? fleetAffiliation.getId() : null);
    }

}
