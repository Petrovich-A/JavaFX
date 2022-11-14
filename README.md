<div align="center">
  <img src="https://github.com/Petrovich-A/JavaFX/blob/master/gif/Employee-Management.png" 
     alt="logo" width="400" height="auto" />
  <h1>Employee sort application</h1>
  <br />
<!-- Badges -->
<p>
<img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/m/Petrovich-A/JavaFX">
<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Petrovich-A/JavaFX">
</p>
<h4>
    <a href="https://github.com/Petrovich-A/JavaFX#notebook_with_decorative_cover-table-of-contents">Project description</a>
  <span> · </span>
    <a href="https://github.com/Petrovich-A/JavaFX/tree/master/src/main/resources/JavaDoc">Documentation</a>
</h4>
</div>
<br />

<!-- Table of Contents -->
# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#zap-about-the-project)
  * [Project description](#memo-project-description)
  * [How it works](#construction_worker-how-it-works)
  * [Introducing FMXL](#art-Introducing-FMXL)
  * [Screenshots](#camera-screenshots)
  * [Tech Stack](#pushpin-tech-stack)
- [Getting Started](#toolbox-getting-started)
  * [Run Locally](#running-run-locally)
- [Contributing](#wave-contributing)

<!-- About the Project -->
## :zap: About the Project

## :memo: Project description

This is a basic project on **Java** digned applying JavaFX [**JavaFX**](https://docs.oracle.com/javafx/2/overview/jfxpub-overview.htm) and [**IntelliJ IDEA**](https://www.jetbrains.com/idea/) by JetBrains
File -> New -> Project... -> JavaFX ). JavaFX is a toolkit [**GUI**](https://en.wikipedia.org/wiki/Graphical_user_interface) (Graphical user interface) for Java. Another words, JavaFX is a
set of graphics and media packages that enables developers to design, create, test, debug, and deploy rich client applications that operate consistently across diverse platforms.
This app represents a simple form with a filter, which displays the given data from the database. The 'database table' looks like this:

<h6>

| Column name      | Datatype    | PRIMARY KEY        | NOT NULL            | UNIQUE KEY         | AUTO_INCREMENT     |
|------------------|-------------|--------------------|---------------------|--------------------|--------------------|
| id_employee      | int         | :white_check_mark: | :white_check_mark:  | :white_check_mark: | :white_check_mark: |
| personnel_number | int         |                    | :white_check_mark:  | :white_check_mark: |                    |
| name             | varchar(45) |                    | :white_check_mark:  |                    |                    |
| surname          | varchar(45) |                    | :white_check_mark:  |                    |                    |

</h6>

The filter allows you to display the results of a SQL query and retrieve an employee list based on the text request and
the item (id, personnel number, name, surname) you select from a drop-down list. The "find" button launches the filtering process.

<!-- How it works -->
### :construction_worker: How it works

Each application created with JavaFX consists of a hierarchy of some basic components:
* stages;
* scenes
* nodes.

Stage basically represents a window. Application can have multiple stages, but needs at least one. A scene represents the contents 
of a stage. Each stage can have multiple scenes, which you can switch. Each scene can contain various components, which are called nodes.
These can be controls like buttons or labels or even layouts, which can contain multiple nested components. Each scene can have one nested node,
but it can be a layout, which can host multiple components. The nesting can be multiple levels deep - layouts can contain 
other layouts and regular components.

Simply put, each application can have multiple stages - windows. Each stage can switch multiple scenes. Scenes contain nodes.

Each JavaFX application needs to have a main application class. That is - a class that extends `javafx.application.Application`. 
You also need to override the abstract method from the Application class - `public void start(Stage primaryStage) throws Exception`.
Inside the `main()` method, we can launch our application using `Application.launch()`.

In order to show a window we need to load stage, which we have as an input parameter in the start method. The same way we 
can load another components of windows and multiple icons.
Finally, we can easily show it using `primaryStage.show()`.

Main class look something like this:

```java
public void start(Stage stage) throws Exception {
  FXMLLoader fxmlLoader = new FXMLLoader(EmployeeApplication.class.getResource("employeeSort.fxml"));
  Path path = Paths.get("src/main/resources/icons/bussiness-man.png");
  InputStream inputStream = new FileInputStream(path.toFile());
  Image image = new Image(inputStream);
  Scene scene = new Scene(fxmlLoader.load(), 600, 400);
stage.setScene(scene);
stage.setTitle("Employee app");
stage.getIcons().add(image);
stage.show();
}
```
<!-- Introducing FMXL -->
### :art: Introducing FMXL

in addition to the procedural construction of your UI, we can use declarative XML markup. It turns out XML's hierarchical
structure is a great way to describe a hierarchy of components in the user interface. This XML format specific to JavaFX 
is called [**FXML**](https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html). Here
you can define all your components and their properties and link it with a Controller, which is responsible for managing interactions.

```xml
fx:controller="by.javafx.petrovich.demo.controller.ShowEmployeesController"
```

When loading basicFXML.fxml, the loader will find the name of the controller class, as specified in the `FXML` by

```java
fx:controller=" org.scenebuilder.BasicFXMLController"
```

Then the loader will create an instance of that class, in which it will try to inject all the objects that have an `fx:id`
tag in the FXML and are marked with the `@FXML` annotation in the controller class.

In this sample, the FXMLLoader will create the label based on `<Label ... fx:id="label"/>`, and it will inject the label
instance into the `@FXML` field.

```java
private Label label;
```


The FXMLLoader also parses all the custom event handlers included, like `"#onFindButtonClick"`, and it will look for the
matching methods in the controller that register those action handlers. In my case, `@FXML`

```java
protected void onFindButtonClick()
```

Finally, when the whole FXML has been loaded, the FXMLLoader will call the controller's initialize method.

<h6>
:bookmark: Editing
</h6>

> While the FXML file can be edited within the IDE, it is not recommended, as the IDE provides just basic syntax checking
and autocompletion, but not visual guidance. The best approach is opening the `FXML` file with `Scene Builder`, where all the changes will be saved to the file.

<!-- Screenshots -->

### :camera: Screenshots

<div align="center"> 
  <img src="https://github.com/Petrovich-A/JavaFX/blob/master/gif/Employee_app_2022-11-08.gif" 
    alt="screenshot" width="600" height="400" />
</div>

<!-- TechStack -->
### :pushpin: Tech Stack

<details>
  <summary>Tech Stack</summary>
  <ul>
    <li><a href="https://java.com/en//">Java</a></li>
    <li><a href="https://openjfx.io/">JavaFX</a></li>
    <li><a href="https://gluonhq.com/products/scene-builder/">Scene Builder</a></li>
    <li><a href="https://www.mysql.com/">MySQL</a></li>
  </ul>
</details>

<!-- Getting Started -->
## 	:toolbox: Getting Started

<!-- Run Locally -->
### :running: Run Locally

Clone the project

```bash
  git clone https://github.com/Petrovich-A/JavaFX.git
```

Optionally you can download the `.jar` file by following the [**link**](https://github.com/Petrovich-A/JavaFX/blob/master/out/artifacts/EmployeeSort/EmployeeSort.jar) 
and populate the database with data by running the sql [**query**](https://github.com/Petrovich-A/JavaFX/blob/e362be0bb08104884ce65bcb894ac1cd6d985ccf/dateBase/INSERT%20EMPLOYEES.sql).

<!-- Contributing -->
## :wave: Contributing

I appreciate your assistance in a code review n discussing some issues I encountered with.
Thank you, buddy. :sunglasses:

<a href="https://github.com/Khodyko/zapchastiSpring/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Khodyko/zapchastiSpring" />
</a>

Made with [contrib.rocks](https://contrib.rocks).
