# Expense Algorithm Visualization

This application allows users to interact with my algorithm and line chart visuals through a GUI developed with the JavaFX library. The algorithm is written in Java and it calculates varying expense rates in aggregation over varying time periods. Use cases include, personal finance management, calculating billing cycles, service charge estimates, and whatever you find applicable.

## Getting Started

1. Make sure you have Java 12 setup on your machine
2. Clone the project 
*(This may take a minute or two as I've also included JavaFX package files in the project for those who don't know how to install the dependencies)*
3. Navigatie to the project root
```/expense-algorithm-visualization/FinanceVisualization/```
4. Unzip dist.zip
5. Navigate to un-zipped folder
```/expense-algorithm-visualization/FinanceVisualization/dist```
6. Run command to run jar executable
```java --module-path ./lib --add-modules javafx.controls,javafx.fxml -Dfile.encoding=UTF-8 -jar ExpenseCalculator.jar```



## Usage

The application is very simple and intuitive to use. It abstracts much of the algorithm's logic from the user in the form of a GUI.

1. Input the expense data for a particular service/charge
2. Add the charge
3. A message will appear confirming a successful input, otherwise, follow the message's instructions
3. Reproduce ***Step 1*** until you are satisfied with the number of services/charges you've inputed
4. Click visualize and inspect the graphic for your own needs
5. To start a new session, just close the graph and return to ***Step 1***

## Example
I've originally written this algorithm to calculate finances for a React Native app I'm developing. This will be the example use-case I will be providing.

Services include...
* $99/year for deployment in Apple Store
* $25/one-time-charge for deployment in Android Store
* $25/month for Firebase as database


First I would enter information for apple store deployment. The title can be anything you want.
[Image of Apple Deployment Information](https://i.imgur.com/Rhz2HZL.png)
Then click 'Add Expenses' and you should see a green confirmation message. If not, follow the instructions of the error message.
[Image of Added Expenses](https://imgur.com/XYxuFOW)
Do the same for Android store information. ***Make sure the information for charge rate is correct*** In this case, The charge rate will be $25 per month. 
[Image of Android Store Deployment Information](https://imgur.com/cZGRU18)
*I will also add Firebase's but I won't show this in an image.*
Click on 'Visualize' to see the graph. *(Note: Your past entries will dissapear upon visualization so make sure you inputted all that you need.)*
[Image of Visualized Graph](https://imgur.com/0DvlD05)

## Contributing

Please read [CONTRIBUTING.md](https://github.com/lusterane/expense-algorithm-visualization) for details on my code of conduct, and the process for submitting pull requests to me.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
