export function changePage (page) {
    return {
        type: 'PAGE_CHANGED',
        payload: page,
    }
}