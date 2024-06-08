/*
Crea una nueva clase llamada Conductor. 
Esta clase tendrá como atributos String nombre, el nombre del conductor, y Coche asignado, el coche que ha sido asignado al conductor.

Crea un método donde se le pasará un numero de bastidor y una flota de vehículos. 
El método comprobará que el coche con ese número de bastidor existe en esa flota y se lo asignará al conductor. 
Para buscar el coche en la flota, crea un método nuevo en la clase Flota que recibirá un número de bastidor 
y devolverá el objeto coche si lo encuentra o null en caso contrario.

Crea otro método que muestre el coche asignado al conductor con la frase:
“Coche asignado a {nombre}: {coche}”.
En tu clase principal, crea 2 conductores, asígnale uno de los coches a uno de los conductores 
y muestra por pantalla el coche asignado al conductor.
*/
package ejercicios.Unidad8_Objetos.Clases_y_objetos;

import java.util.ArrayList;
import java.util.List;

public class U8_08 {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        car1.brand = "Ford";
        car1.model = "Smax";
        car1.color = "Azul";
        car1.chassisNumber = 11111;

        car2.brand = "Seat";
        car2.model = "Ibiza";
        car2.color = "Rojo";
        car2.chassisNumber = 22222;

        car3.brand = "Ford";
        car3.model = "Smax";
        car3.color = "Azul";
        car3.chassisNumber = 33333;

        Fleet fleet = new Fleet();
        fleet.addCar(car1);
        fleet.addCar(car2);
        fleet.addCar(car3);
        System.out.println(fleet);

        int chassisNumber = 44444;
        if (fleet.removeCar(chassisNumber)) {
            System.out.println("Se ha eliminado el coche con número de bastidor: " + chassisNumber);
        } else {
            System.out.println("No existe ningún coche con número de bastidor " + chassisNumber);
        }
        System.out.println(fleet);

        Driver driver1 = new Driver();
        driver1.name = "Juan";
        driver1.assignedCar(11111, fleet);
        driver1.showAssignedCar();

        Driver driver2 = new Driver();
        driver2.name = "Pepe";
        driver2.assignedCar(22222, fleet);
        driver2.showAssignedCar();

    }
}

public class Car {
    public String brand, model, color;
    public int chassisNumber, speed = 0;

    public void increaseSpeed(int increase) {
        speed += increase;
    }

    public void reduceSpeed(int decrement) {
        speed -= decrement;
    }

    @Override
    public String toString() {
        return String.format("es un %s %s de color %s con número de bastidor %d", brand, model, color, chassisNumber);
    }
}

public class Fleet {
    public List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public boolean removeCar(int chassisNumber) {
        int foundCars = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (chassisNumber == cars.get(i).chassisNumber) {
                cars.remove(cars.get(i));
                foundCars++;
            }
        }
        return (foundCars == 0) ? false : true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Car car : cars) {
            builder.append(String.format("El car %s %s es de color %s con número de bastidor %d\n", car.brand,
                    car.model, car.color, car.chassisNumber));
        }
        return builder.toString();
    }

    public Car searchCar(int chassisNumber) {
        for (Car car : cars) {
            if (car.chassisNumber == chassisNumber) {
                return car;
            }
        }
        return null;
    }
}

public class Driver {
    public String name;
    public Car assignedCar;

    public void assignedCar(int chassisNumber, Fleet fleet) {
        if (fleet.searchCar(chassisNumber) != null) {
            assignedCar = fleet.searchCar(chassisNumber);
        }
    }

    public void showAssignedCar() {
        System.out.println(String.format("Coche asignado a %s %s", name, assignedCar.toString()));
    }
}
