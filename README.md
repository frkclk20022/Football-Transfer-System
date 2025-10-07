Football Transfer System

This project provides a football transfer management system for club managers and player managers. Users can register, log in, and manage transfer offers within the system.

Features

User roles: Club Manager and Player Manager

Player Manager: Can add players, list them, view incoming offers, and respond to them

Club Manager: Can send offers to players and view responses

Offer response strategies:

ManualResponseStrategy → manual response

SmartThresholdStrategy → intelligent response based on player market value

Project Structure

Main.java → Program entry point and user interface

ClubManager.java → Club manager class

PlayerManager.java → Player manager class

Player.java → Player class

Offer.java → Offer class

OfferResponseStrategy.java → Offer response strategy interface

ManualResponseStrategy.java → Manual response strategy

SmartThresholdStrategy.java → Intelligent response strategy

User.java → Represents users in the system

Usage

Run the program (Main.java).

Register or log in.

Interact with menus according to your role:

Club Manager: Send offers, view incoming responses

Player Manager: Add players, view offers, respond

Requirements

Java 8 or higher

Contributing

Contributions are welcome! You can submit a pull request or open an issue to suggest improvements.

License
