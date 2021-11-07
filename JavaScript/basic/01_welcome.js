/**
 * Example 01 - Welcome
 *
 * Includes:
 * 1. Basic console print
 * 2. Basic function with parameter
 */

// Simply prints Hello World to the console
console.log("Hello, World");

// Function to print out a given string.
function hello(name) {
  return "Hello, " + name;
}

console.log(hello("Zana!"));

/**
 * Example 02 - Class
 *
 * Includes:
 * 1. Basic class and constructor use (with optional field)
 * 2. Basic Switch statement
 */
class Greet {
  name;
  age;

  constructor(name, age = 0) {
    this.name = name;
    this.age = age;
  }

  hello() {
    return "Hello " + this.name + "!";
  }

  sentence() {
    switch (this.age) {
      case 0:
        return `Hello, my name is ${this.name}! and I am a new born`;

      case 1:
        return `Hello, my name is ${this.name}! and I am a toddler`;

      default:
        return `Hello, my name is ${this.name}! and I am a ${this.age} years old.`;
    }
  }
}

// Initialize the Greet class
let greeting = new Greet("Zana", 1);

// Use the functions
console.log(greeting.hello());
console.log(greeting.sentence());
