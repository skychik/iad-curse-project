export function pageReducer (state = {}, action) {
  switch (action.type) {
      case 'PAGE_CHANGED':
          switch (action.payload) {
              case 'pile':
              case 'events':
              case 'pageNotFound':
              case 'loops':
              case 'profile':
                  return action.payload;
              default:
                  return 'unknown_page';
          }
      default:
          return state;
  }
}