import NoteButton from "@/components/NoteButton.vue";

export function formatText(text) {
    if (!text) return '';
    const parts = [];
    const regex = /<note id="(\d+)" title="([^"]+)" \/>/g;
    let lastIndex = 0;
    let match;
    while ((match = regex.exec(text)) !== null) {
        if (match.index > lastIndex) {
            parts.push({type: 'text', content: text.substring(lastIndex, match.index)});
        }
        parts.push({type: 'button', id: match[1], title: match[2]});
        lastIndex = regex.lastIndex;
    }
    if (lastIndex < text.length) {
        parts.push({type: 'text', content: text.substring(lastIndex)});
    }
    return parts;
}
