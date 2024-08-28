/**
 * Integration tests for <e-retrospective-app>
 */
import { expect } from 'chai';
import RetrospectiveApp from '../RetrospectiveApp';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('RetrospectiveApp Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      RetrospectiveApp.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-retrospective-app>', async () => {
        const appUnderTest = await inject('<e-retrospective-app></e-retrospective-app>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
