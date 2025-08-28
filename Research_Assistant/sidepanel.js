// Wait until the DOM is fully loaded
document.addEventListener('DOMContentLoaded', () => {
    // Fetch saved notes from local storage
    chrome.storage.local.get(['researchNotes'], (result) => {
        if (result.researchNotes) {
            document.getElementById('notes').value = result.researchNotes;
        }
    });

    // Attach button event listeners
    const summarizeBtn = document.getElementById('summarizeBtn');
    const saveNotesBtn = document.getElementById('saveNotesBtn');

    if (summarizeBtn) summarizeBtn.addEventListener('click', summarizeText);
    if (saveNotesBtn) saveNotesBtn.addEventListener('click', saveNotes);
});

// --- Function to summarize selected text ---
async function summarizeText() {
    try {
        // Get the active tab
        const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });

        // Run script in tab to grab selected text
        const [{ result }] = await chrome.scripting.executeScript({
            target: { tabId: tab.id },
            func: () => window.getSelection().toString()
        });

        if (!result || result.trim() === "") {
            showResult('⚠️ Please select some text first');
            return;
        }

        // Send text to backend API
        const response = await fetch('http://localhost:8080/api/research/process', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            //sending the query as per the required or mentioned format of "content" and "operation"
            body: JSON.stringify({ content: result, operation: 'summarize' })
        });

        if (!response.ok) throw new Error(`API error: ${response.status}`);

        const text = await response.text();
        showResult(text.replace(/\n/g, '<br>'));
    } catch (error) {
        showResult('❌ Error: ' + error.message);
    }
}

// --- Function to save notes ---
function saveNotes() {
    const notes = document.getElementById('notes').value;
    chrome.storage.local.set({ researchNotes: notes }, () => {
        alert('✅ Notes saved successfully');
    });
}

// --- Function to display results ---
function showResult(content) {
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = `
        <div class="result-item">
            <div class="result-content">${content}</div>
        </div>
    `;
}
