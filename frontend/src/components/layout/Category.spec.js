import Category from './Category';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('Category component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<Category />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

