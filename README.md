<h4>
    <a href="https://github.com/Louis3797/awesome-readme-template/">Project description</a>
  <span> · </span>
    <a href="https://github.com/Louis3797/awesome-readme-template">Documentation</a>
  <span> · </span>
    <a href="https://github.com/Louis3797/awesome-readme-template/issues/">Report Bug</a>
  <span> · </span>
    <a href="https://github.com/Louis3797/awesome-readme-template/issues/">Request Feature</a>
  </h4>

<br />

<!-- Table of Contents -->

# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#star2-about-the-project)
    * [How it works](#how-it-works)
    * [Screenshots](#camera-screenshots)
    * [Tech Stack](#space_invader-tech-stack)
    * [Features](#dart-features)
- [Contributing](#wave-contributing)
    * [Code of Conduct](#scroll-code-of-conduct)

<!-- About the Project -->

## :star2: About the Project

## Project description

This is a basic project that uses FXML, created with [**IntelliJ IDEA**](https://www.jetbrains.com/idea/) by JetBrains (
File -> New -> Project... -> JavaFX ).
Application designed applying JavaFX. [**JavaFX**](https://docs.oracle.com/javafx/2/overview/jfxpub-overview.htm) is a
set of graphics and media packages that enables developers to design, create, test, debug, and deploy rich client
applications that operate consistently across diverse platforms.
[**FXML**]() is an XML-based user interface markup language created by Oracle Corporation for defining the user
interface of a JavaFX application.

The application is a simple form with a filter, which displays the given data from the database.
The 'database table' looks like this:

<h6>

| Column name      | Datatype    | PRIMARY KEY        | NOT NULL            | UNIQUE KEY         | AUTO_INCREMENT     |
|------------------|-------------|--------------------|---------------------|--------------------|--------------------|
| id_employee      | int         | :white_check_mark: | :white_check_mark:  | :white_check_mark: | :white_check_mark: |
| personnel_number | int         |                    | :white_check_mark:  | :white_check_mark: |                    |
| name             | varchar(45) |                    | :white_check_mark:  |                    |                    |
| surname          | varchar(45) |                    | :white_check_mark:  |                    |                    |

</h6>
<!-- How it works -->

## :star2: How it works

Briefly, in the main Application class, the FXMLLoader will load `basicFXML.fxml` from the jar/classpath, as specified by
```java
FXMLLoader.load(getClass().getResource("BasicFXML.fxml"));
```

When loading basicFXML.fxml, the loader will find the name of the controller class, as specified in the `FXML` by
```java
fx:controller=" org.scenebuilder.BasicFXMLController"
```

Then the loader will create an instance of that class, in which it will try to inject all the objects that have an fx:id
tag in the FXML and are marked with the @FXML annotation in the controller class.

In this sample, the FXMLLoader will create the label based on <Label ... fx:id="label"/>, and it will inject the label
instance into the `@FXML` private Label label; field.

The FXMLLoader also parses all the custom event handlers included, like "#handleButtonAction", and it will look for the
matching methods in the controller that register those action handlers. In this case, @FXML private void
handleButtonAction(ActionEvent event).

Finally, when the whole FXML has been loaded, the FXMLLoader will call the controller's initialize method.

Editing

While the FXML file can be edited within the IDE, it is not recommended, as the IDE provides just basic syntax checking
and autocompletion, but not visual guidance.

The best approach is opening the FXML file with Scene Builder, where all the changes will be saved to the file.

Scene Builder can be launched to open the file:

Application allows to show data using the filter in the drop-down list. You need to select the field by which the search
will be performed. The search is carried out by the field for entering a value for the filter, respectively. Data
filtering is launched with the "find" button

<!-- Screenshots -->

### :camera: Screenshots

<div align="center"> 
  <img src="https://github.com/Petrovich-A/JavaFX/blob/master/gif/Employee_app_2022-11-08.gif" 
    alt="screenshot" width="600" height="400" />
</div>


<!-- TechStack -->
### :space_invader: Tech Stack

<details>
  <summary>Tech Stack</summary>
  <ul>
    <li><a href="https://java.com/en//">Java</a></li>
    <li><a href="https://openjfx.io/">JavaFX</a></li>
    <li><a href="https://gluonhq.com/products/scene-builder/">Scene Builder</a></li>
    <li><a href="https://www.mysql.com/">MySQL</a></li>
  </ul>
</details>

### Special thanks:

<!-- Contributing -->

## :wave: Contributing

I appreciate your help in kind code review. Thanks, guys.

<a href="https://github.com/Khodyko/awesome-readme-template/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Khodyko/awesome-readme-template" />
</a>
