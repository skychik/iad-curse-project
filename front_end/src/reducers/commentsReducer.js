export function commentsReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COMMENTS_PENDING": break;
        case "FETCH_COMMENTS_FULFILLED":
            return action.payload;
        case "PUT_COMMENT_LOOP_FULFILLED":
            return changeCommentPLoopNumber(state, action.payload.id, true, action.payload.value, true);
        case "PUT_COMMENT_POOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, false, action.payload.value, true);
        case "REMOVE_COMMENT_LOOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, true, action.payload.value, false);
        case "REMOVE_COMMENT_POOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, false, action.payload.value, false);
        case "FETCH_COMMENTS_REJECTED":
    }
    return state;
}

// не оптимальный алгоритм: не прекращает работу, если уже нашел нужный comment
function changeCommentPLoopNumber(data, id, isLoop, total, wasPut) {
    if (data.comments.length === 0) {
        return data;
    } else {
        return {
            ...data,
            comments: data.comments.map(
                (comment) => {
                    if (comment.id === id) {
                        if (isLoop) {
                            return {
                                ...comment,
                                loopsNumber: total,
                                loopWasPut: wasPut
                                // ... хотя бы здесь не идет вглубь
                            }
                        } else {
                            return {
                                ...comment,
                                poopsNumber: total,
                                poopWasPut: wasPut
                                // ... хотя бы здесь не идет вглубь
                            }
                        }
                    } else {
                        return changeCommentPLoopNumber(comment, id, isLoop, total, wasPut)
                    }
                })
        }
    }
}