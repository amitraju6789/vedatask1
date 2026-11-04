# Veda Technology Business & Service Management System

A Java object-oriented application built for Day 35 (Project Day 1 of 4) representing operations, service categories, training programs, and customer inquiries at Veda Technology.

## Key Features
- **Object-Oriented Inheritance:** Encapsulates service offerings and training programs through an abstract base class (`Offering`).
- **Collections & Mappings:** Manages customer records using `HashMap` and active catalogs/requests using `ArrayList`.
- **Search & Filtering:** Dynamic query filtering across catalog offerings and service request statuses.
- **Robust Exception Handling:** Custom business exceptions (`BusinessException`) handle missing records and invalid inputs gracefully.

## How to Compile & Run
1. Open terminal in root directory (`Veda-Task-1`).
2. Compile: `javac task35/VedaBusinessManagementSystem.java`
3. Execute: `java task35.VedaBusinessManagementSystem`