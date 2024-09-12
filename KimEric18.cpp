/**
 * This assignment and program is my first look into the language C++. This program takes no user input and utilizes
 * the classes that can be created in C++, which can't be created in C. In this program, I created functions that 
 * print, get, and set variables in certain way.
 * 
 * @author     Kim, Eric
 * @assignment ICS 212 Assignment 18
 * @date       13 April 2024
 */

#include <iostream>

class Complex {
public: 
  // constructor & member functions
  // Default constructor initializes real and imaginary to 0
  Complex() {
    real = 0;
    imaginary = 0;
  }

  // Define a member function called print that uses cout to print the complex number
  void print() const {
    if (imaginary >= 0) {
      std::cout << "(" << real << " + " << imaginary << "i)" << std::endl;
    }
    else {
      std::cout << "(" << real << " - " << -imaginary << "i)" << std::endl;
    }
  }

  // set function
  void set (double re, double im) {
    real = re;
    imaginary = im;
  }

  // get functions
  double getReal() const {
    return real;
  }
  double getImaginary() const {
    return imaginary;
  }

private:
  // data members & utility functions... create doubles real and iaginary.
  double real;
  double imaginary;

};

int main() {
  // Setup test for constructor
  std::cout << "Test the constructor." << std::endl;
  Complex complex1;
  std::cout << "Complex number complex1 is: ";
  complex1.print();
  std::cout << std::endl;

  // Setup test for sest function
  std::cout << "Test the one set() function." << std::endl;
  Complex complex2;
  complex2.set(3.3, -4.4);
  std::cout << "Complex number complex2 is: ";
  complex2.print();
  std::cout << std::endl;

  // Setup test for get functions
  std::cout << "Test the two get() functions." << std::endl;
  Complex complex3;
  complex3.set(5.5, 6.6);
  std::cout << "Complex number complex3's real part is: " << complex3.getReal() << std::endl;
  std::cout << "Complex number complex3's imaginary part is: " << complex3.getImaginary() << std::endl;

  return 0;
}
