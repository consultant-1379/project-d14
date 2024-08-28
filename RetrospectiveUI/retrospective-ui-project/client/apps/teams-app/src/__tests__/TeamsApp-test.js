/**
 * Integration tests for <e-teams-app>
 */
import { expect } from 'chai';
import TeamsApp from '../TeamsApp';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('TeamsApp Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      TeamsApp.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-teams-app>', async () => {
        const appUnderTest = await inject('<e-teams-app></e-teams-app>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
