# screen-shot

This project's scope is for hackathon purposes. The main goal was to create a feature that can take screenshot to an URL automatically and also that by passing
some parameters, the user/consumer could specify which portion/element of the URL want to capture or crop.

For this, Selenium dependency was used to open the URL, navigate to to the website and finally take the snapshot of it.

The screenshots are currently saved on a folder whithin the project that is autmatically created the first time. These folder and files are excluded by the .gitignore

## How to Run it
To Run the project just execute the main sprinboot java class and it will execute it automatically. This is not a service though, however the compatibility with Spring enable us to make it in a future iteration
