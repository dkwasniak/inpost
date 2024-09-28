# ADR-000: Use of Clean Architecture with MVVM Pattern

**Date:** 2024-09-28  
**Status:** Approved  
**Author:** Damian Kwasniak

---

## Context

This project is a business application intended for a large-scale user base of approximately 3 million customers. The project will be developed and maintained by multiple developers over several years. It is not a typical MVP (Minimum Viable Product) but a complex and long-term product that requires high maintainability, scalability, and testability.

Given these characteristics, selecting a robust architectural pattern is crucial for maintaining code quality and enabling smooth collaboration. Two main architectural concerns were considered:

1. **Testability and Scalability:**
    - The architecture must support extensive testing (unit tests, integration tests, and UI tests) due to the application's critical nature.
    - The codebase should be highly modular to facilitate independent feature development by different teams.

2. **Separation of Concerns:**
    - The architecture should enable clear separation of business logic, data management, and UI components.
    - This separation will allow for smooth development and maintenance, even as the project evolves over time.

3. **Long-term maintainability:**
    - The architecture should ensure that the codebase remains clean and easy to modify, even as it grows and new features are added.
    - Different parts of the system should be independently replaceable or upgradable without affecting the entire project.

## Decision

We decided to adopt **Clean Architecture** in combination with the **MVVM (Model-View-ViewModel) pattern** for the following reasons:

1. **Clean Architecture:**
    - Clean Architecture provides a well-defined structure that separates application layers, ensuring that business logic is independent of UI and data sources.
    - The core layers (e.g., domain and use cases) are decoupled from the data sources (network, databases), making it easy to replace implementations without impacting the business logic.
    - This architecture improves **testability**, as each layer can be tested in isolation.
    - Its layered structure will enable long-term maintainability by making the codebase modular and easy to extend or refactor.

2. **MVVM Pattern:**
    - The MVVM pattern is well-suited for Android development, providing a clear separation of concerns between the UI (`Screen`), the business logic (`ViewModel`), and the data (`Model`).
    - **ViewModels** handle UI-related data and lifecycle management, making it easy to preserve state during configuration changes (e.g., screen rotation).
    - The **Model** contains the business and data logic, while the **View** layer (activities and fragments) remains free from business logic, focusing only on displaying data and handling user interactions.

3. **Compatibility with Clean Architecture:**
    - MVVM works seamlessly with Clean Architecture by ensuring that ViewModels in the Presentation layer interact with Use Cases in the Domain layer, while data is provided through the Repository pattern in the Data layer.
    - This combination allows for a clean separation between UI, business logic, and data sources, improving both testability and maintainability.

## Rationale

1. **Scalability for a large development team:**
    - The Clean Architecture pattern provides a modular structure that allows different teams to work on separate parts of the system independently.
    - MVVM, with its separation of UI and business logic, enables smooth integration and reduces merge conflicts in shared codebases.

2. **Testability:**
    - With Clean Architecture, business logic can be tested in isolation without the need for UI dependencies.
    - The MVVM pattern supports easy testing of ViewModels and allows for mocking of data layers without involving the UI.

3. **Long-term maintainability:**
    - This project will be maintained and evolved over several years. Clean Architecture ensures that changes in one part of the application (e.g., switching a data source from REST API to GraphQL) do not ripple through other layers.
    - MVVM enables clear state management for the UI, making future modifications and feature extensions easier to implement.

4. **Industry-proven patterns:**
    - Both Clean Architecture and MVVM are well-established in the Android community and have extensive documentation, tooling, and community support, which will facilitate onboarding new developers and ensuring best practices are followed.

## Consequences

1. **Positive:**
    - High testability and maintainability.
    - Clear separation of concerns, making it easier to add new features or refactor existing code.
    - Modular codebase that enables independent development by different teams.
    - Easier debugging and troubleshooting due to clear delineation of responsibilities between layers.

2. **Negative:**
    - Initial setup and adherence to the architecture principles require a steeper learning curve, especially for new or less experienced developers.
    - Increased number of layers and components might introduce some boilerplate code and added complexity.
    - More upfront development time is required compared to simpler architectures, but it pays off in the long run.

## Implementation Details

1. **Project Structure:**
    - **Data Layer:** Contains `Dto` models, Repositories, and data sources (e.g., local database, network).
    - **Domain Layer:** Contains use cases, domain models, and business logic.
    - **Presentation Layer:** Contains ViewModels, UI models, and view components.

2. **Mapping Strategies:**
    - Each layer has its own data models to ensure separation of concerns.
    - Extension functions or dedicated mappers will be used to convert data between layers.

3. **Testing:**
    - Unit tests for each layer (e.g., use case testing in the Domain layer, ViewModel testing in the Presentation layer).
    - Integration tests for Repository implementations in the Data layer.

## Future Considerations

1. As the project grows, consider adopting **feature modules** to further decouple different parts of the application.
2. Regularly review the architecture to ensure that the chosen patterns remain suitable as new features and requirements are introduced.
3. Document additional architecture decisions using ADRs to maintain alignment across the team.

---

**Summary:** Adopting Clean Architecture combined with the MVVM pattern ensures a scalable, testable, and maintainable codebase that will support a large-scale project over several years. This decision positions the project for long-term success, allowing multiple teams to collaborate effectively and deliver a high-quality product to millions of users.

The decision will be regularly revisited to ensure that it continues to meet project needs as it evolves.
