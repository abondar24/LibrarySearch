# LibrarySearch

## Setting
A books management is to be implemented which consists of the following parts:
- There are several authors who are familiar with the details of e-mail address, first and last
name
- There are regular books that have a title, a short description, one or more authors, and an
ISBN number
- In addition, magazines are also included that have a title, one or more authors, the publication
date and an ISBN number

## Functionalities
The following functionalities should be applicable on the managed books / magazines:
- Reading all data from multiple CSV files in the "data" folder. These can be found in the
attachment as a ZIP archive. The structure of the files should be self-explanatory.
- Return all books / magazines with all details
- Find and return a book / magazine using an ISBN number
- Find and return all the books / magazines of an author
- Sort and return all books / magazines by title

## Build and run
```yaml 
mvn clean install

java -jar target/LibrarySearch-1.0-SNAPSHOT.jar

```
