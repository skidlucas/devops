Spoon Maven Plugin Examples
===

## Introduction

Some examples of spoon-maven-plugin usages.

The project is separated in 3 modules:

- processors: Contains all processors necessary for examples. Other modules have a dependency to this module to access at processors.
- analysis-code: Example of analysis code. Defines the plugin with a custom processor which analysis the source code of the module.
- transformation-code: Example of transformation code. Defines the plugin with custom processors which transform the origin source code in a output directory (target directory of the module).

## Usage

You can test the project with a `mvn clean install` command on the root of the project (parent module) to execute all examples (all sub modules) or execute the command on the example you would like to test.

In each `pom.xml` of sub modules, you have the example of the usage of the Spoon's maven plugin.

For more information, you can go to the [Spoon's Maven Plugin repository](https://github.com/SpoonLabs/spoon-maven-plugin).