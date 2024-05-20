export function Input(props){
    // eslint-disable-next-line react/prop-types
    const {id,label,error,onChange} = props;
    return (
        <div className="mb-3">
            <label htmlFor={id} className="form-label">
                {label}
            </label>
            <input id={id}
            className={error ? "form-control is-invalid" : "form-control"}
            onChange={onChange}
            type="text" />
            <div className="invalid-feedback">{error}</div>
        </div>

    );
}