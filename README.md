# 📖 Smart Research Assistant  

** About the Project and Features  
The **Smart Research Assistant** is a productivity-focused tool that combines the simplicity of a **Chrome Extension** with the intelligence of a
**Spring Boot backend powered by Spring AI**.  

Its purpose is to help users summarize lengthy web content such as blogs, articles, or research papers into **concise and meaningful summaries**.
With a **single click**, users can send webpage content to the backend and instantly receive a structured summary that saves time and effort.  

This project is especially beneficial for **students, researchers, and professionals** who need to quickly extract important insights without reading every line. 
The extension provides features such as **AI-powered summarization, a lightweight and user-friendly UI, and local storage of summaries** for future reference.  

Its design is **modular**, making it easier to enhance with features such as multiple summarization formats (short, bullet-points, detailed), export options (PDF, DOCX, TXT),
and even multilingual support. The ultimate goal of the Smart Research Assistant is to bring **AI-driven research support directly into the browser**, offering **speed, accuracy, and convenience** in one integrated solution.  



** Tech Stack and Architecture Summary  
The Smart Research Assistant follows a **clean architecture** that separates the concerns of frontend, backend, and AI processing.  

- **Frontend (Chrome Extension):** Built with **HTML, CSS, JavaScript** to extract webpage text, interact with the backend, and display results. Uses **Chrome Storage API** to save summaries locally.  
- **Backend (Spring Boot):** Provides **RESTful APIs** to handle requests.  
- **Spring AI Integration:** Connects with **Large Language Models (LLMs)** like OpenAI or Google Gemini for summarization.  
- **Architecture Pattern:** Simple client-server model that ensures modularity and scalability.
-  ┌───────────────────────────────┐
 │        Chrome Extension       │
 │  - UI (HTML, CSS, JS)         │
 │  - Extracts webpage content   │
 │  - Sends requests via fetch() │
 │  - Stores notes (Chrome API)  │
 └───────────────┬───────────────┘
                 │
                 ▼
 ┌───────────────────────────────┐
 │     Spring Boot Backend       │
 │  - REST Controller (/api/..)  │
 │  - Service Layer              │
 │  - Integrates with Spring AI  │
 └───────────────┬───────────────┘
                 │
                 ▼
 ┌───────────────────────────────┐
 │         AI Model (LLM)        │
 │  - OpenAI / Gemini / etc.     │
 │  - Generates summary          │
 └───────────────────────────────┘

This is the main structure which ease the building of the project.The service layer completly handle the whole flow upto the integration with ai.

