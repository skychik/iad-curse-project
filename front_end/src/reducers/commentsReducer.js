export function commentsReducer(state = {}, action) {
    switch (action.type) {
        case "FETCH_COMMENTS_PENDING": break;
        case "FETCH_COMMENTS_FULFILLED":
            return action.payload;
        case "PUT_COMMENT_LOOP_FULFILLED":
            console.log("------");
            console.log("PUT_COMMENT_LOOP_FULFILLED");
            console.log(state);
            const ans = changeCommentPLoopNumber(state, action.payload.id, true, action.payload.value, action.payload.succeed);
            console.log("commentsReducer:");
            console.log(ans);
            console.log("------");
            return ans;
        case "PUT_COMMENT_POOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, false, action.payload.value, action.payload.succeed);
        case "REMOVE_COMMENT_LOOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, true, action.payload.value, !action.payload.succeed);
        case "REMOVE_COMMENT_POOP_FULFILLED":
            if (state === null) return state;
            return changeCommentPLoopNumber(state, action.payload.id, false, action.payload.value, !action.payload.succeed);
        case "FETCH_COMMENTS_REJECTED":
        case "PUT_COMMENT_LOOP_REJECTED":
        case "PUT_COMMENT_POOP_REJECTED":
            console.log(action.payload);
            break;
        default:
            return state;
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