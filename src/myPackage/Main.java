package myPackage;

class Car {
	private String color, brand;
	private boolean isAvailable;
	
	public Car(CarBuilder builder) {
		this.color = builder.color;
		this.brand = builder.brand;
		this.isAvailable = builder.isAvailable;
	}
	
	public Car(Car car) {
		this.color = car.color;
		this.brand = car.brand;
		this.isAvailable = car.isAvailable;
	}
	
	public Car clone() {
		return new Car(this);
	}
	
	@Override
	public String toString() {
	    return "Car{brand=" + brand + ", color=" + color + ", available=" + isAvailable + "}";
	}
	
	static class CarBuilder {
		private String color, brand;
		private boolean isAvailable;
		
		public CarBuilder() {
			this.color = "";
			this.brand = "";
			this.isAvailable = false;
		}
		
		public CarBuilder setColor(String color) {
			this.color = color;
			return this;
		}
		
		public CarBuilder setBarnd(String brand) {
			this.brand = brand;
			return this;
		}
		
		public CarBuilder setAvailbility(boolean isAvailable) {
			this.isAvailable = isAvailable;
			return this;
		}
		
		public Car build() {
			return new Car(this);
		}
	}
}



class Main {
	public static void main(String[] args) {
		Car.CarBuilder carBuilder = new Car.CarBuilder();
		Car car1 = carBuilder.setBarnd("honda").setColor("red").build();
		Car car2 = carBuilder.setBarnd("bmw").setAvailbility(false).build();
		Car car3 = car2.clone();
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
		
	}
}