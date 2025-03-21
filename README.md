# Note Pro App
Note Pro is a simple yet powerful note-taking Android app built with Java, following the MVVM architecture pattern and utilizing Room Database for local storage. With this app, you can easily create, edit, delete, and search for notes. The app also includes a feature to mark notes as favorites for easy access.

This project is designed for Android developers who want to understand how to implement Room Database with MVVM architecture and build a functional note-taking application.

## Features
Add Notes: Quickly add new notes with titles and content.
Edit Notes: Modify existing notes to keep them up-to-date.
Delete Notes: Delete notes when they are no longer needed.
Favorite Notes: Mark notes as favorites for easy access and viewing.
Search Notes: Search through notes with an integrated search bar.
Room Database: Persistent storage using Room Database for efficient note management.
MVVM Architecture: Clean and maintainable code with the Model-View-ViewModel (MVVM) pattern.

## Technologies Used
Java: Main programming language for app development.
Room Database: Local database to store notes persistently.
MVVM Architecture: Follows the Model-View-ViewModel (MVVM) pattern for clean code separation.
LiveData: Used for data binding and ensuring UI updates automatically with data changes.
RecyclerView: Displays the list of notes efficiently.
SearchView: Implemented for searching through notes.
Material Design: Provides a modern and user-friendly UI.
## Screenshots
Here are some screenshots of the Note Pro app in action:

| Screenshot 1 | Screenshot 2 | Screenshot 3 |
|--------------|--------------|--------------|
| ![Screenshot 1](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-50-50-643_com.nouroeddinne.notepro.jpg) | ![Screenshot 2](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-50-57-897_com.nouroeddinne.notepro.jpg) | ![Screenshot 3](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-51-00-847_com.nouroeddinne.notepro.jpg) |

| Screenshot 4 | Screenshot 5 | Screenshot 6 |
|--------------|--------------|--------------|
| ![Screenshot 4](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-51-10-140_com.nouroeddinne.notepro.jpg) | ![Screenshot 5](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-51-37-544_com.nouroeddinne.notepro.jpg) | ![Screenshot 6](https://raw.githubusercontent.com/noureddinne21/NotePro/refs/heads/master/Screenshot_2024-11-05-19-51-42-280_com.nouroeddinne.notepro.jpg) |

## Installation
To get started with the Note Pro app locally, follow these steps:

Clone the repository:

git clone https://github.com/noureddinne21/NotePro.git
### Open the project in Android Studio:

Launch Android Studio and open the cloned repository.
### Build and Run:

Select a virtual device or connect a physical device.
Click "Run" to build and run the app.
### Setup Database:

The app uses Room Database for note storage. No extra setup is required; the app will automatically create the necessary tables on first launch.

## How to Use
Add a Note: Click the "Add" button to create a new note. Enter a title and description.
Edit a Note: Long press on a note from the list to edit it.
Delete a Note: Swipe left or right on a note to delete it.
Mark Favorite: Tap the star icon on any note to mark it as a favorite.
Search Notes: Use the search bar to filter through your notes.
## Architecture
This app follows the MVVM (Model-View-ViewModel) design pattern, which helps to separate the UI (View) from the data and business logic (Model) and ensures a clean, scalable architecture.

Model: Manages the data and business logic. In this app, it consists of the Room Database entities and the DAO (Data Access Object).
View: Represents the UI components (Activities/Fragments) where users interact with the app.
ViewModel: Holds and manages UI-related data in a lifecycle-conscious way. It communicates between the View and the Model.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For technical support or feature requests, please contact:
*[noureddinne.office@gmail.com]*

