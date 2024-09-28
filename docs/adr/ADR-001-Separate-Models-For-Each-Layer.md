# ADR-001: Use of Separate Models for Each Layer and Mapping via Extension Functions

**Date:** 2024-09-28  
**Status:** Approved  
**Author:** Damian Kwasniak

---

## Context

To implement the application using the principles of Clean Architecture, we need to define a strategy for data modeling and transformation across layers. In Clean Architecture, each layer should have its own models to ensure separation of concerns and maintain flexibility. This approach will also make the system more modular and easier to test, as changes in one layer will not directly affect others.

Key aspects considered include:

1. **Separation of responsibilities between layers:**
    - The **Data layer** deals with raw data representations (e.g., API responses, database models).
    - The **Domain layer** focuses on business logic and should be isolated from external data structures.
    - The **Presentation layer** uses models tailored for UI needs.

2. **Maintainability and scalability:**
    - Given the complex nature of the project and its long-term development, separating models and responsibilities will enable more effective scaling and maintainability.
    - Each layer will be able to evolve independently without breaking other parts of the system.

3. **Choosing a mapping strategy:**
    - Various strategies were considered, such as using **extension functions**, **dedicated mapper classes**, and **static utility functions** for converting models between layers.

## Decision

We decided to use **separate models for each layer** and handle data transformation using **extension functions**:

1. **Separate Models for Each Layer:**
    - Each layer will have its own data models:
        - **Data Layer:** Models are suffixed with `Dto` (e.g., `CustomerDto`, `ShipmentDto`).
        - **Domain Layer:** Models are defined without suffixes (e.g., `Customer`, `Shipment`).
        - **Presentation Layer:** Models are suffixed with `UiModel` (e.g., `CustomerUiModel`, `ShipmentUiModel`).

2. **Mapping Using Extension Functions:**
    - We chose **extension functions** as the primary mechanism for mapping between layers (`Dto` -> `Domain`, `Domain` -> `UiModel`) because:
        - **Readability:** Extension functions are concise and clearly indicate the direction of transformation.
        - **Testability:** They can be tested in isolation without dependency on external classes.
        - **Simplicity:** Extension functions are straightforward and require minimal boilerplate compared to mapper classes.

3. **Mapper Functions Location:**
    - Functions mapping from `Dto` to `Domain` models will reside in the **Data layer**.
    - Functions mapping from `Domain` to `UiModel` will be placed in the **Presentation layer**.
    - This ensures that each layer is responsible for its own data transformations, minimizing cross-layer dependencies.

## Rationale

1. **Separation of concerns:**
    - Each layer has its own responsibility and should not depend on the internal details of other layers.
    - By using separate models, changes in one layer (e.g., changes to an API response) do not directly impact business logic or the UI layer.

2. **Ease of refactoring and maintainability:**
    - With separate models, refactoring is simplified, as changes in the Data layer (e.g., renaming a field in `CustomerDto`) will not propagate to the Domain or Presentation layers.

3. **Readability and clarity:**
    - Extension functions make it clear where data is being transformed and which layer is responsible for which transformation.

4. **Modularity:**
    - Each layer can be independently developed, tested, and modified, supporting the long-term scalability of the application.

## Consequences

1. **Positive:**
    - Clear separation of responsibilities, making it easier to modify and test each layer independently.
    - Better testability, as each layer can be mocked or replaced without affecting others.
    - Greater flexibility for future changes, such as switching data sources or changing the presentation format.

2. **Negative:**
    - Increased number of model classes and mapping functions, which can add some complexity and boilerplate code.
    - Additional upfront effort required to implement and maintain mapping logic, especially in complex scenarios.

## Implementation Details

1. **Project Structure:**
    - **Data Layer:** Contains `Dto` models and `toDomain()` extension functions for mapping to domain models.
    - **Domain Layer:** Contains core business models and logic.
    - **Presentation Layer:** Contains `UiModel` classes and `toUiModel()` extension functions for mapping from domain models.

2. **Mapping Strategy:**
    - Map data from `Dto` models to `Domain` models in the Data layer.
    - Map from `Domain` models to `UiModel` in the Presentation layer.
    - Use `extension functions` for clarity and ease of use.

3. **Testing:**
    - Unit tests for each mapper function.
    - Integration tests for validating that mappings work correctly across layers.

## Future Considerations

1. If the number of mappings significantly increases, consider introducing dedicated mapper classes to avoid cluttering the codebase with too many extension functions.
2. Regularly review mapping logic to ensure that it remains clear and maintainable as the project grows.
3. Document any complex mappings or transformations to help onboard new developers.

---

**Summary:** Using separate models for each layer and mapping them using extension functions provides a clean and maintainable approach that aligns with the principles of Clean Architecture. This strategy improves testability, scalability, and clarity, making it well-suited for a long-term project involving multiple developers.

The decision will be revisited periodically to ensure that it continues to meet project requirements as the application evolves.
