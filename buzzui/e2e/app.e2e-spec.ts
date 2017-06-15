import { BuzzuiPage } from './app.po';

describe('buzzui App', () => {
  let page: BuzzuiPage;

  beforeEach(() => {
    page = new BuzzuiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
